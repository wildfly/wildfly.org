package org.wildfly.site.model;

import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Session(
    String title,
    LocalTime start,
    LocalTime end,
    String type,
    @JsonProperty("abstract")
    String sessionAbstract,
    List<Speaker> speaker,
    List<ConferenceLink> links
) {
    public String getCssClass(String confStatus) {
        if ("done".equals(confStatus)) {
            return "c-session c-session-blue done";
        } else {
            if ("other".equals(type)) {
                return "c-session c-session-other";
            } else {
                return "c-session c-session-blue";
            }
        }
    }
}
