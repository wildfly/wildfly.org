package org.wildfly.site;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.quarkiverse.roq.testing.RoqAndRoll;
import io.quarkus.qute.Engine;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.wildfly.site.model.Releases;
import org.wildfly.site.model.Releases.JavaSe;
import org.wildfly.site.model.Releases.JavaSeSupport;
import org.wildfly.site.model.Releases.Release;

@QuarkusTest
@RoqAndRoll
public class RoqSiteTest {
    @Inject
    Engine engine;

    private final ObjectMapper json = new ObjectMapper();

    @Test
    public void testGen() {
        // All pages will be generated/validated during test setup
    }

    @Test
    public void supportMatrixMatchesReleaseMetadata() throws Exception {
        JsonNode matrix = json.readTree(Files.readString(Path.of("target/roq/support-matrix.json")));
        assertEquals("wildfly.org/support-matrix", matrix.path("schema").asText());
        assertEquals(2, matrix.path("schema_version").asInt());
        assertEquals(Releases.SUPPORT_MATRIX_MIN_MAJOR, matrix.path("min_wildfly_major").asInt());

        ObjectMapper yaml = new ObjectMapper(new YAMLFactory());
        List<Release> data = yaml.readerForListOf(Release.class)
                .readValue(Path.of("data/releases.yaml").toFile());
        List<Release> included = new Releases(data).supportMatrix();
        assertEquals(included.size(), matrix.path("releases").size());
        for (int i = 0; i < included.size(); i++) {
            Release release = included.get(i);
            JsonNode rendered = matrix.path("releases").get(i);
            assertEquals(release.version(), rendered.path("version").asText());
            assertEquals(json.valueToTree(release.javaSe()), rendered.get("java_se"), release.version());
            if ("Final".equals(release.qualifier())) {
                assertNotNull(release.javaSe(), release.version());
                for (JavaSeSupport support : List.of(release.javaSe().standard(), release.javaSe().preview())) {
                    assertNotNull(support.recommended(), release.version());
                    assertTrue(support.qualified().contains(support.recommended()), release.version());
                    assertEquals(support.qualified().size(), support.qualified().stream().distinct().count());
                }
            }
        }
    }

    @Test
    public void supportMatrixRendersIndependentAndMissingDistributions() throws Exception {
        Releases fixture = new Releases(List.of(
                release("36.0.0.Final", null),
                release("37.0.0.Final", new JavaSe(
                        new JavaSeSupport("21", List.of("17", "21")),
                        new JavaSeSupport("17", List.of("17")))),
                release("38.0.0.Final", new JavaSe(new JavaSeSupport(null, List.of()), null)),
                release("39.0.0.Final", new JavaSe(null, new JavaSeSupport("21", List.of("21")))),
                release("40.0.0.Beta1", null)));
        String template = Files.readString(Path.of("content/support-matrix.qute.json"))
                .replace("cdi:releases.supportMatrix", "releases.supportMatrix");
        JsonNode releases = json.readTree(engine.parse(template).data("releases", fixture).render())
                .path("releases");

        assertEquals(4, releases.size());
        assertEquals("37.0.0.Final", releases.get(0).path("version").asText());
        assertEquals("21", releases.get(0).at("/java_se/standard/recommended").asText());
        assertEquals("17", releases.get(0).at("/java_se/preview/recommended").asText());
        assertTrue(releases.get(1).at("/java_se/standard/recommended").isNull());
        assertEquals(0, releases.get(1).at("/java_se/standard/qualified").size());
        assertTrue(releases.get(1).at("/java_se/preview").isNull());
        assertTrue(releases.get(2).at("/java_se/standard").isNull());
        assertEquals("21", releases.get(2).at("/java_se/preview/recommended").asText());
        assertTrue(releases.get(3).path("java_se").isNull());
    }

    private static Release release(String version, JavaSe javaSe) {
        return new Release(version, version, "2025-08-04", version.endsWith("Final") ? "Final" : "Beta",
                null, List.of(), javaSe);
    }
}
