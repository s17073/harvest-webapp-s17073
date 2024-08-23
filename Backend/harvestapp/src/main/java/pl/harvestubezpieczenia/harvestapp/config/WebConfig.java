package pl.harvestubezpieczenia.harvestapp.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("Get", "Post", "Put", "Delete")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
