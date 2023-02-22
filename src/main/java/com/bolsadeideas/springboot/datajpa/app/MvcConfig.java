package com.bolsadeideas.springboot.datajpa.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  private final Logger logger = LoggerFactory.getLogger(MvcConfig.class);

  /*@Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    WebMvcConfigurer.super.addResourceHandlers(registry);

    String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
    logger.info("resourcePath: " + resourcePath);

    registry
        .addResourceHandler("/uploads/**")
        .addResourceLocations(resourcePath);
  }*/
}
