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

    /**
     * Minimum WildFly major version included in support-matrix.json (WFLY-18250).
     */
    public static final int SUPPORT_MATRIX_MIN_MAJOR = 37;

    public Release latest() {
        return list.stream().filter(r -> r.qualifier.equals("Final")).findFirst().orElse(list.get(0));
    }

    /**
     * Releases included in the machine-readable support matrix (WildFly 37+).
     */
    public List<Release> supportMatrix() {
        return list.stream()
                .filter(Release::includedInSupportMatrix)
                .toList();
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
        List<Link> links,
        @JsonProperty("java_se")
        JavaSe javaSe
    ) {
        public Date releaseDate() throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            return formatter.parse(date);
        }

        public String majorVersion() {
            return version.split("\\.")[0];
        }

        public int majorVersionNumber() {
            return Integer.parseInt(majorVersion());
        }

        public boolean includedInSupportMatrix() {
            return majorVersionNumber() >= SUPPORT_MATRIX_MIN_MAJOR;
        }
    }

    /**
     * Java SE support is maintained independently for Standard WildFly and WildFly Preview.
     * An absent entry means that no support data has been recorded for that distribution.
     */
    public record JavaSe(
        JavaSeSupport standard,
        JavaSeSupport preview
    ) {}

    /**
     * Fully qualified LTS releases, and the recommended member of that list.
     * Qualifying LTS and non-LTS releases are intentionally not represented yet.
     * Qualification here does not imply Jakarta EE certification on that Java SE version.
     */
    public record JavaSeSupport(
        String recommended,
        List<String> qualified
    ) {}

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
