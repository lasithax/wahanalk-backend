package com.agile.wahanalk_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applies to all endpoints
          .allowedOrigins("*")  // Allows all origins; change to specific origins for security
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
          .allowedHeaders("*");  // Allows all headers
      }
    };
  }
}

