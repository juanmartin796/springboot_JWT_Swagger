package com.martin.motomandado;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MotomandadoApplication {
	@Value("${encriptacionContrasena.strength}")
	private int strength;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		//Este numero varia en funcion a la velocidad del procesador que tengamos. 
		//Se usa para complicar a los ataques de fuerza bruta, extendendiendo el tiempo de respuesta
        return new BCryptPasswordEncoder(strength);
    }

	public static void main(String[] args) {
		SpringApplication.run(MotomandadoApplication.class, args);
	}
}
