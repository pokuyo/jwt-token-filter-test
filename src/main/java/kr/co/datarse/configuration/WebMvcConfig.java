package kr.co.datarse.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Value("${front.host.url}")
	private String frontHost;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				//.allowedOrigins("*")
				.allowedOrigins(frontHost)
				.allowedMethods("*")
				.allowedHeaders("*");
	}
}
