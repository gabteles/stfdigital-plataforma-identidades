package br.jus.stf.plataforma.userauthentication;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/**
 * @author Tomas.Godoi
 * 
 * @since 1.0.0
 * @since 25.04.2016
 */
@SpringBootApplication(scanBasePackages = "br.jus.stf")
@EnableEurekaClient
public class ApplicationContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationContextInitializer.class, args);
	}

	@Profile("development")
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2WebServer() throws SQLException {
		return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8195");
	}

}
