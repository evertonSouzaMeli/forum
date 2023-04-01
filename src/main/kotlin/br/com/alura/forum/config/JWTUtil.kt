package br.com.alura.forum.config

import br.com.alura.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date

// Classe responsavel por gerar a estrutura do token

@Component
class JWTUtil(
    private val usuarioService: UsuarioService
) {

    private val expiration: Long = 6000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? =
        Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setIssuedAt(Date(System.currentTimeMillis() + expiration))
            .signWith(HS512, secret.toByteArray())
            .compact()

    fun isValid(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val username = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body.subject
        val user = usuarioService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}