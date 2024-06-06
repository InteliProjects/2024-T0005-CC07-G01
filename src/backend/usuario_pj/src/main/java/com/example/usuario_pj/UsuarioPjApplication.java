package com.example.usuario_pj;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioPjApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioPjApplication.class, args);
	}

	@Value("${server.port}")
	private int port;

}
