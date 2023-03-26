package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


//Classe que vai gerenciar as configurações de segurança da aplicação
// Quando damos o bootRun o spring olha pra essa classe para ver quais as configurações aplicadas
@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .authorizeRequests {
                it.antMatchers("/topicos").hasAuthority("LEITURA_ESCRITA")
                it.antMatchers("/h2-console/**").permitAll()
                it.anyRequest().authenticated()
            }
            .headers { it.frameOptions().disable() }
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .formLogin { it.disable() }
            .httpBasic(Customizer.withDefaults())
            .csrf { it.ignoringAntMatchers("/h2-console/**") }
            .build()
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}