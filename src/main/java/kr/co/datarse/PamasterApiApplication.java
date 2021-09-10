package kr.co.datarse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PamasterApiApplication {
	
	static {
		System.setProperty("spring.config.location", "classpath:/application.yaml,classpath:log4jdbc.log4j2.properties,classpath:/persistence.yaml");
	}

	public static void main(String[] args) {
		SpringApplication.run(PamasterApiApplication.class, args);
	}

}
