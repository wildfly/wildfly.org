/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.wildfly.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.wildfly.site.model.Contributor;

/**
 * This creates a view of the {@code contributors.yaml} file. Using default Quarkus mapping does not seem to work
 * for {@link java.time.LocalDate}. This is a simple workaround.
 *
 * @author <a href="mailto:jperkins@ibm.com">James R. Perkins</a>
 */
@ApplicationScoped
@Named("ContributorService")
public class ContributorService {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory())
            .findAndRegisterModules()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private List<Contributor> contributors;

    @PostConstruct
    void init() {
        final Path dataPath = Path.of(".", "data", "contributors.yaml");
        try (BufferedReader reader = Files.newBufferedReader(dataPath, StandardCharsets.UTF_8)) {
            this.contributors = mapper.readValue(reader, new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        contributors.sort(Comparator.comparing(Contributor::name));
    }

    public List<Contributor> contributors() {
        return contributors;
    }
}
