package info.vdsi.nags.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableAutoConfiguration
@SpringBootApplication
public class MovieflexApp {

	public static void main(String[] args) {
		
		SpringApplication.run(MovieflexApp.class, args);
		
		System.out.println("hii");
	}

}
