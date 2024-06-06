package br.edu.inteli.redisapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


/**
 * Main application class
 */
@SpringBootApplication
public class RedisApiApplication {

    /**
     * Main method to start the application
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(RedisApiApplication.class, args);
	}

}
