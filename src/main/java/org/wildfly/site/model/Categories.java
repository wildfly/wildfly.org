package org.wildfly.site.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkiverse.roq.data.runtime.annotations.DataMapping;

@DataMapping(value = "guides")
public record Categories(List<Category> categories) {
    public record Category(
        @JsonProperty("category")
        String title,
        @JsonProperty("cat-id")
        String id,
        List<Group> groups,
        List<Guide> guides
        ) { }
    public record Group(String title, List<Guide> guides) {}
    public record Guide(String title, String url, String description) {}
}
