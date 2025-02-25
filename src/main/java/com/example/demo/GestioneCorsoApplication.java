package com.example.demo;

import com.example.demo.entity.Ruolo;
import com.example.demo.repository.RuoloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
public class GestioneCorsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestioneCorsoApplication.class, args);
	}
    @Bean
	public CommandLineRunner runner(RuoloRepository ruoloRepository){
		return args -> {
			if (ruoloRepository.findByNome("USER").isEmpty()){
				ruoloRepository.save(
						Ruolo.builder().nome("USER").build()
				);
			}
		};
	}
}
