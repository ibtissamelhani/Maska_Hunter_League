package org.youcode.maska_hunters_league;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class MaskaHuntersLeagueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaskaHuntersLeagueApplication.class, args);
    }

}
