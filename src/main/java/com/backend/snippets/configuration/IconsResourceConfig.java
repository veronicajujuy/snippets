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
        // Configurar el manejador para servir los iconos desde ambas ubicaciones:
        // 1. Primero busca en la carpeta de uploads (./uploads/icons/)
        // 2. Luego busca en los recursos estáticos (classpath:/static/icons/uploaded/)
        registry.addResourceHandler("/icons/uploaded/**")
                .addResourceLocations(
                    "file:" + uploadedIconsDir + "/",
                    "classpath:/static/icons/uploaded/"
                );
    }
}
