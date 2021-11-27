package edu.unifacef.aluguelDeCarros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class AluguelDeCarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluguelDeCarrosApplication.class, args);
	}

}
