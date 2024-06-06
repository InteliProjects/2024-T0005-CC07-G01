package com.example.usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}

	 /**
     * The port number on which the server is running.
     * The value is injected from the application properties.
     */
	@Value("${server.port}")
	private int port;


}
