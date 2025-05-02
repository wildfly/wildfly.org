package org.wildfly.site;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class Extensions {

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String wordLimit(String text, int limit) {
        String[] words = text.split("\\s+");
        if (words.length < limit) {
            return text;
        }
        return String.join(" ", Arrays.copyOfRange(words, 0, limit)) + "...";
    }

    public static String join(List<?> list, String separator) {
        return String.join(separator, list.stream().map(Object::toString).toList());
    }
}
