package com.comercio_electrico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories(basePackages = "dao")
@SpringBootApplication
public class ComercioElectricoPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComercioElectricoPostgresqlApplication.class, args);
	}

}
