package br.com.alura.forum.config

import br.com.alura.forum.model.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

//Aqui  geramos o token para o usuario através das informações enviadas
class JWTLoginFilter(
    private val authenticationManager: AuthenticationManager?,
    private val jwtUtil: JWTUtil) : UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {

        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)

        val token = UsernamePasswordAuthenticationToken(username, password)

        return authenticationManager?.authenticate(token)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val user = (authResult?.principal as UserDetails)
        response?.addHeader("Authorization", "Bearer ${jwtUtil.generateToken(user.username, user.authorities)}")
    }
}
