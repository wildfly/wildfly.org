package org.wildfly.site;

import io.quarkiverse.qute.web.markdown.runtime.MdConverter;
import io.quarkiverse.roq.frontmatter.runtime.model.DocumentPage;
import io.quarkiverse.roq.plugin.asciidoctorj.runtime.AsciidoctorJConverter;
import io.quarkus.arc.Arc;
import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class PageExtensions {
//    public static String convert(DocumentPage page) {
//        final String rawText = page.info().rawContent();
//        final var ext = page.info().sourceFileExtension();
//
//        if (ext.equals("adoc") || ext.equals("asciidoc") || ext.equals("ad")) {
//            AsciidoctorJConverter converter = Arc.container().beanInstanceSupplier(AsciidoctorJConverter.class).get().get();
//
//            return converter.apply(rawText.replaceAll("\\{[#/]asciidoc}", ""));
//        } else if (ext.equals("md")) {
//            MdConverter converter = Arc.container().beanInstanceSupplier(MdConverter.class).get().get();
//            return converter.html(rawText);
//        } else {
//            return rawText;
//        }
//    }


}
