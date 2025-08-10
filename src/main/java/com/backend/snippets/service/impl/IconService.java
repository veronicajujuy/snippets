package com.backend.snippets.service.impl;

import com.backend.snippets.exception.BadRequestException;
import com.backend.snippets.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class IconService {
    private final Path uploadedIconsDir;

    public IconService(@Value("${application.storage.uploaded-icons-dir}") String uploadedIconsDir) throws IOException {
        // Convertir a ruta absoluta para evitar problemas de comparación
        Path path = Path.of(uploadedIconsDir);
        if (!path.isAbsolute()) {
            path = path.toAbsolutePath().normalize();
        }
        this.uploadedIconsDir = path;
        Files.createDirectories(this.uploadedIconsDir);
    }

    public String saveIcon512(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new BadRequestException("El archivo no puede estar vacío");
            }

            String contentType = file.getContentType();
            if(contentType == null || !contentType.equalsIgnoreCase("image/png")) {
                throw new BadRequestException("El archivo debe ser una imagen PNG");
            }

            BufferedImage img = ImageIO.read(file.getInputStream());
            if (img == null) {
                throw new BadRequestException("El archivo no es una imagen válida");
            }

            BufferedImage finalImage = img;
            if (img.getWidth()!= 512 || img.getHeight() != 512) {
                finalImage = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
                finalImage.getGraphics().drawImage(img, 0, 0, 512, 512, null);
            }

            String filename = "icon_"+ UUID.randomUUID()+".png";
            Path target = this.uploadedIconsDir.resolve(filename).normalize();

            // Aseguramos que el directorio uploadedIconsDir exista
            if (!Files.exists(uploadedIconsDir)) {
                Files.createDirectories(uploadedIconsDir);
            }

            // La validación de seguridad de rutas
            if(!target.toAbsolutePath().startsWith(this.uploadedIconsDir.toAbsolutePath())){
                throw new BadRequestException("Ruta de archivo inválida por razones de seguridad");
            }

            try (OutputStream os = Files.newOutputStream(target, StandardOpenOption.CREATE_NEW)){
                boolean ok = ImageIO.write(finalImage, "png", os);
                if (!ok) {
                    throw new BadRequestException("No se pudo escribir la imagen en el archivo");
                }

                return "/icons/uploaded/" + filename;
            }
        } catch (IOException e) {
            throw new BadRequestException("Error al procesar el archivo: " + e.getMessage());
        } catch (Exception e) {
            throw new BadRequestException("Error inesperado al procesar el icono: " + e.getMessage());
        }
    }

    public boolean iconExists(String filename) {
        Path iconPath = this.uploadedIconsDir.resolve(filename).normalize();
        return Files.exists(iconPath) && iconPath.startsWith(this.uploadedIconsDir);
    }

    public boolean predefinedIconExists(String iconName) {
        try {
            ClassPathResource resource = new ClassPathResource("static/icons/" + iconName);
            return resource.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public String getIconUrl(String iconName) {
        if (iconExists(iconName)) {
            return "/icons/uploaded/" + iconName;
        } else if (predefinedIconExists(iconName)) {
            return "/icons/" + iconName;
        }
        throw new NotFoundException("Icon not found: " + iconName);
    }
}
