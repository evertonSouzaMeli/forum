package br.com.alura.forum.config

import br.com.alura.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val usuario: Usuario): UserDetails {

    override fun getAuthorities() = usuario.roles

    override fun getPassword(): String = usuario.password

    override fun getUsername(): String = usuario.email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}