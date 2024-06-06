package com.example.usuario_pf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioPfApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioPfApplication.class, args);
	}

	@Value("${server.port}")
	private int port;

}
