package org.wildfly.site;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.quarkiverse.roq.frontmatter.runtime.model.Page;
import io.quarkiverse.roq.frontmatter.runtime.model.RoqCollection;
import io.quarkus.qute.TemplateExtension;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.wildfly.site.model.ConferenceData;

@ApplicationScoped
@Named("conferences")
public class ConferenceService {
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
    private final Map<String, ConferenceData> conferences = new HashMap<>();

    @TemplateExtension
    public static RoqCollection sortEvents(RoqCollection collection) {
        if ("events".equals(collection.id())) {
            collection.sort((o1, o2) ->
            {
                String key1 = (String) o2.data("data-key");
                String key2 = (String) o1.data("data-key");
                if (key1 == null) {
                    return -1;
                }
                if (key2 == null) {
                    return 1;
                }

                return key1.compareTo(key2);
            });
        }

        return collection;
    }

    public ConferenceService() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();

        loadConferenceData();
    }

    public ConferenceData get(Page page) {
        return conferences.get(page.data().getString("data-key"));
    }

    private void loadConferenceData() {
        Path dataPath = Path.of(".", "data", "conference");

        try (Stream<Path> paths = Files.walk(dataPath)) {
            paths.filter(path -> path.toFile().getName().endsWith("yaml"))
                .map(this::readFile)
                .filter(Objects::nonNull)
                // Reverse order to make the template simpler
                .sorted((o1, o2) -> o2.date.compareTo(o1.date))
                .forEach(conf -> {
                    conferences.put(conf.key, conf);
                });
        } catch (IOException e) {
            //
        }
    }

    private ConferenceData readFile(Path path)  {
        ConferenceData conferenceData = null;
        try {
            File file = new File(path.toString());
            String key = file.getName().replace(".yaml", "");
            conferenceData = objectMapper.readValue(file, ConferenceData.class);
            conferenceData.key = key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return conferenceData;
    }
}
