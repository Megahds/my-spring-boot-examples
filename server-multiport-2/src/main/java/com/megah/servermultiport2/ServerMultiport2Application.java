package com.megah.servermultiport2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.megah.servermultiport2.config.EmbeddedTomcatConfiguration;

@SpringBootApplication
@Import(EmbeddedTomcatConfiguration.class)
public class ServerMultiport2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServerMultiport2Application.class, args);
	}

}
