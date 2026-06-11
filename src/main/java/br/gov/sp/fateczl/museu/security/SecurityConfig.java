package br.gov.sp.fateczl.museu.security;

import org.springframework.context.annotation.Bean;

public class SecurityConfig {

    // Preparação para o futuro ;)
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Libera explicitamente a pasta de imagens e o manifesto para qualquer visitante
                        .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );
        return http.build();
    }*/
}
