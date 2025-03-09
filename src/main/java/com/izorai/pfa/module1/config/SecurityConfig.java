package com.izorai.pfa.module1.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour permettre les requêtes POST depuis Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Permet toutes les requêtes sans authentification
                )
                .formLogin(login -> login.disable()) // Désactive le formulaire de login par défaut
                .httpBasic(basic -> basic.disable()); // Désactive l'authentification basique

        return http.build();
    }
}
