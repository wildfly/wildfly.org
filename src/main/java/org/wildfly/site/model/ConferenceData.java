package org.wildfly.site.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConferenceData {
    public String key;
    public LocalDate date;
    public LocalTime start;
    public LocalTime end;
    public String status;
    @JsonProperty("video-id")
    public String videoId;
    public String video;
    public String rsvp;
    public String feedback;
    public String registration;
    @JsonProperty("agenda")
    public List<Session> sessions;

    public List<Session> sessionsByStatus() {
        if ("done".equals(status)) {
            return sessions.stream().filter(session -> "talk".equals(session.type()))
                .toList();
        } else {
            return sessions;
        }
    }

    @Override
    public String toString() {
        return "ConferenceData{" +
            "key='" + key + '\'' +
            ", date=" + date +
            ", start=" + start +
            ", end=" + end +
            ", status='" + status + '\'' +
            ", videoId='" + videoId + '\'' +
            ", video='" + video + '\'' +
            ", rsvp='" + rsvp + '\'' +
            ", feedback='" + feedback + '\'' +
            ", registration='" + registration + '\'' +
            ", agenda=" + sessions +
            '}';
    }

}
