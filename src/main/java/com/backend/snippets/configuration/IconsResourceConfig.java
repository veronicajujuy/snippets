package com.backend.snippets.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IconsResourceConfig implements WebMvcConfigurer {

    @Value("${application.storage.uploaded-icons-dir}")
    private String uploadedIconsDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/icons/uploaded/**")
                .addResourceLocations("file:" + uploadedIconsDir + "/");
    }
}
