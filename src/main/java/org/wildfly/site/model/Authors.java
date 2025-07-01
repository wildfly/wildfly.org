package org.wildfly.site.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.quarkiverse.roq.data.runtime.annotations.DataMapping;
import io.quarkiverse.roq.frontmatter.runtime.model.DocumentPage;
import io.vertx.core.json.JsonArray;

@DataMapping(value = "authors", parentArray = true)
public record Authors(List<Author> list) {
    public record Author(String id,
                         String name,
                         String bio,
                         String location,
                         String occupation,
                         String avatar,
                         String twitter,
                         String emailhash) {

        @Override
        public String toString() {
            return name;
        }
    }

    public Author byId(String id) {
        return list.stream()
            .filter(author -> Objects.equals(id, author.id))
            .findFirst()
            .orElse(null);
    }

    public List<Author> buildAuthorList(DocumentPage page) {
        var key = page.data().getValue("author");
        List<String> authorList = new ArrayList<>();
        if (key instanceof String authorId) {
            authorList.add(authorId);
        } else if (key instanceof JsonArray authorArray) {
            authorList.addAll((List<String>) authorArray.<String>getList());
        }

        return authorList.stream()
            .map(id -> list.stream()
                .filter(author -> Objects.equals(id, author.id))
                .findFirst()
                .orElse(null))
            .filter(Objects::nonNull)
            .toList();
    }
}
