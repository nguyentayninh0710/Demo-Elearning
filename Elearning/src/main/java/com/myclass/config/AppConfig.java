package com.myclass.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class AppConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		Path userUploadDir = Paths.get("./user-photos/");
		String userUploadPath = userUploadDir.toFile().getAbsolutePath();
		
		registry.addResourceHandler("/user-photos/**").addResourceLocations("file:/"+userUploadPath+"/");
	}
	
	
}
