package com.entrevistador.orquestador;

import com.entrevistador.orquestador.infrastructure.properties.EntrevistaPruebaConst;
import com.entrevistador.orquestador.infrastructure.properties.WebFluxProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({EntrevistaPruebaConst.class, WebFluxProperties.class})
@EnableScheduling
public class OrquestadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrquestadorApplication.class, args);
    }

}