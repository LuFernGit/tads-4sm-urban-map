package br.com.senac.urbanmap.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class ConfiguracaoImagemWeb implements WebMvcConfigurer {

    @Value("${urbanmap.upload.dir-imagens}")
    private String diretorioImagens;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path path = Paths.get(diretorioImagens).toAbsolutePath().normalize();
        String caminhoFinal = "file:" + path.toString() + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(caminhoFinal);
    }


}
