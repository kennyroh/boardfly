package io.github.kennyroh.boardfly.config

import io.github.kennyroh.boardfly.service.CustomUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

  @Autowired
  lateinit var customUserDetailsService: CustomUserDetailsService

  override fun configure(http: HttpSecurity) {
    http
//      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/admin/**").hasAuthority("ADMIN")
      .anyRequest().permitAll()
      .and()
      .formLogin()
      .loginPage("/user/login")
      .defaultSuccessUrl("/user/profile")
      .failureUrl("/user/login?error=true")
      .and()
      .logout()
      .logoutSuccessUrl("/user/login?logout=true")
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder())
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
}
