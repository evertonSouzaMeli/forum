package br.com.alura.forum.config

import br.com.alura.forum.security.JWTAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


// Classe que vai gerenciar as configurações de segurança da aplicação
// Quando damos o bootRun o spring olha pra essa classe para ver quais as configurações aplicadas
@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val configuration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil
) {

    companion object {
        const val LEITURA_ESCRITA = "LEITURA_ESCRITA"
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .headers { it.frameOptions().disable() }
            .authorizeRequests {
                it.antMatchers(POST,"/login").permitAll()
                it.antMatchers(GET, "/topicos").hasAuthority(Companion.LEITURA_ESCRITA)
                it.anyRequest().authenticated()
            }
            .addFilterBefore(JWTLoginFilter(configuration.authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .addFilterBefore(JWTAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .csrf().disable()
            .build()
    }

    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}