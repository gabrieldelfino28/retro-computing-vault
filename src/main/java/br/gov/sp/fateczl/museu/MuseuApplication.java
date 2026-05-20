package br.gov.sp.fateczl.museu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MuseuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuseuApplication.class, args);
	}
}
