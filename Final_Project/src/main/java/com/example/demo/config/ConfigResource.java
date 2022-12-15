package com.example.demo.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigResource implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		Path brandUploadDir = Paths.get("./src/main/resources/static/img/food/");
		
		String brandUploadPath = brandUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/src/main/resources/static/img/food/**")
		.addResourceLocations("file:/" + brandUploadPath + "/");
		
	}
}
