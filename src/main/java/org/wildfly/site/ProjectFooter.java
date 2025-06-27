package org.wildfly.site;

import java.util.List;

import io.quarkiverse.roq.data.runtime.annotations.DataMapping;

//@DataMapping(value = "projectfooter")
public record ProjectFooter(String footer_text,
                            Links links,
                            Links moreLinks) {
    public record Links(String title,
                        List<Link> subfolderItems) {
        public record Link(String page, String url) {}
    }
}
