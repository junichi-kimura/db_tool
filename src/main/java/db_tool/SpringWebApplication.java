package db_tool;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringWebApplication extends SpringBootServletInitializer{

	public static void main(String...args) {
		SpringApplication app = new SpringApplication(SpringWebApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8000"));
		app.run(new String[] {});
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringWebApplication.class);
	}
}
