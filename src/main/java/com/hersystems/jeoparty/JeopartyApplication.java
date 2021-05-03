package com.hersystems.jeoparty;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class JeopartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JeopartyApplication.class, args);
	}

}
