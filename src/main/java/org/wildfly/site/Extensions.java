package org.wildfly.site;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.quarkiverse.qute.web.markdown.runtime.MdConverter;
import io.quarkiverse.roq.frontmatter.runtime.model.DocumentPage;
import io.quarkiverse.roq.plugin.asciidoctorj.runtime.AsciidoctorJConverter;
import io.quarkus.arc.Arc;
import io.quarkus.qute.TemplateExtension;
import org.jsoup.Jsoup;

@TemplateExtension
public class Extensions {

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
/*
    public static String[] tags(RoqCollection collection) {
        Set<String> tags = new HashSet<>();
        collection.forEach(page -> {
            if (page.data().containsKey("tags")) {
                tags.addAll(Arrays.asList(splitTags(page.data().getString("tags"))));
            }
        });

        String[] array = tags.toArray(new String[0]);
        Arrays.sort(array);
        return array;
    }

    public static String[] splitTags(String rawText) {
        return rawText.toLowerCase(Locale.ROOT).split(",");
    }
*/

    public static String wordLimit(String text, int limit) {
        String[] words = text.split("\\s+");
        if (words.length < limit) {
            return text;
        }
        return String.join(" ", Arrays.copyOfRange(words, 0, limit)) + "...";
    }

    public static String stripHtml(String rawText) {
        return Jsoup.parse(rawText).text();
    }

    public static String join(List<?> list, String separator) {
        return String.join(separator, list.stream().map(Object::toString).toList());
    }
}
