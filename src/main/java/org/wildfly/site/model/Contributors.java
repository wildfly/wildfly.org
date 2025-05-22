package org.wildfly.site.model;

import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkiverse.roq.data.runtime.annotations.DataMapping;

@DataMapping(value = "contributors", parentArray = true)
public record Contributors(List<Contributor> list) {
    public Contributors {
        if (list != null) {
            list.sort(Comparator.comparing(o -> o.id));
        }
    }
    public record Contributor(
        @JsonProperty("contributor")
        String id,
        String name,
        String bio,
        String github,
        @JsonProperty("signing")
        List<Signing> signings) {
        public record Signing(String id, String key, String fingerprint, String link) {}
    }
}
