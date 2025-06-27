package org.wildfly.site.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkiverse.roq.data.runtime.annotations.DataMapping;

@DataMapping(value = "releases", parentArray = true)
public record Releases(List<Release> list) {
    public static int CURRENT_RELEASE_INDEX = 0;
    public Release latest() {
        return list.stream().filter(r -> r.qualifier.equals("Final")).findFirst().orElse(list.get(0));
    }

    public record Release(
        String version,
        @JsonProperty("version_shortname")
        String versionShortName,
        String date,
        String qualifier,
        @JsonProperty("gpg_key")
        String gpgKey,
        @JsonProperty("link")
        List<Link> links
    ) {
        public Date releaseDate() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            return formatter.parse(date);
        }

        public String majorVersion() {
            return version.split("\\.")[0];
        }
    }
    public record Link(String name,
                       String licence,
                       List<LinkItem> items) {}
    public record LinkItem(String format,
                           String url,
                           String size,
                           String checksum,
                           @JsonProperty("checksum_url")
                           String checksumUrl,
                           String signature,
                           @JsonProperty("signature_url")
                           String signatureUrl) {}
}
