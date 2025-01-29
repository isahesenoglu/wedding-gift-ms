package com.example.weddinggift.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Data
@AllArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Yalnız guest endpoint-ləri üçün interceptor əlavə edirik
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/guests/**"); // Guest endpoint-ləri
    }

}
