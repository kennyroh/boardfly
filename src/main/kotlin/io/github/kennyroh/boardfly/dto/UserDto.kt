package io.github.kennyroh.boardfly.dto

import io.github.kennyroh.boardfly.entity.UserRole
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * A DTO for the {@link io.github.kennyroh.boardfly.entity.User} entity
 */
data class UserDto(

  @field:NotBlank(message = "Username is required.")
  @field:Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
  val username: String,

  @field:Email(message = "Email must be valid.")
  val email: String? = null,

  @field:NotBlank(message = "Password is required.")
  @field:Size(min = 4, max = 100, message = "Password must be between 4 and 100 characters.")
  val password: String,

  val fullName: String? = null,

  val role: UserRole = UserRole.USER
)
