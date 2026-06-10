package br.edu.unir.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //Libera todos os caminhos
                .allowedOrigins("*") //Libera de qualquer origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //Libera todos os métodos
                .allowedHeaders("*");
    }
    
}
