/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.wildfly.site.model;

import java.time.LocalDate;
import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

/**
 *
 * @author <a href="mailto:jperkins@ibm.com">James R. Perkins</a>
 */
public record Contributor(
        @JsonProperty("contributor")
        String id,
        String name,
        String bio,
        String github,
        @JsonProperty("signing")
        SortedSet<Signing> signings) {
    public record Signing(String id, String key, String fingerprint, String link,
                          @JsonDeserialize(using = LocalDateDeserializer.class)
                          @JsonFormat(pattern = "yyyy-MM-dd")
                          LocalDate invalidated,
                          @JsonProperty("invalidated-reason")
                          String invalidatedReason) implements Comparable<Signing> {
        @Override
        public int compareTo(final Signing o) {
            if (invalidated == null && o.invalidated != null) {
                return -1;
            }
            if (invalidated != null && o.invalidated == null) {
                return 1;
            }
            if (invalidated != null) {
                return invalidated.compareTo(o.invalidated);
            }
            return id.compareTo(o.id);
        }
    }
}
