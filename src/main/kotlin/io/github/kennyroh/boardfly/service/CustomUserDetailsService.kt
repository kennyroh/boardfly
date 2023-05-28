package io.github.kennyroh.boardfly.service

import io.github.kennyroh.boardfly.entity.User
import io.github.kennyroh.boardfly.entity.UserRole
import io.github.kennyroh.boardfly.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    val user: User = userRepository.findByUsername(username)
      ?: throw UsernameNotFoundException("User not found with username: $username")

    return CustomUserDetails(user)
  }

  class CustomUserDetails(user: User) : UserDetails {
    private val _user: User = user

    override fun getAuthorities(): Collection<GrantedAuthority> {
      val role: UserRole = _user.role
      val authority = SimpleGrantedAuthority(role.name)
      return listOf(authority)
    }

    override fun getPassword(): String {
      return _user.password
    }

    override fun getUsername(): String {
      return _user.username
    }

    override fun isAccountNonExpired(): Boolean {
      return true
    }

    override fun isAccountNonLocked(): Boolean {
      return true
    }

    override fun isCredentialsNonExpired(): Boolean {
      return true
    }

    override fun isEnabled(): Boolean {
      return true
    }
  }
}
