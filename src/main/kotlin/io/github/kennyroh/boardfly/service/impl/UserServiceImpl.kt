package io.github.kennyroh.boardfly.service.impl

import io.github.kennyroh.boardfly.dto.UserDto
import io.github.kennyroh.boardfly.entity.User
import io.github.kennyroh.boardfly.repository.UserRepository
import io.github.kennyroh.boardfly.service.interfaces.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
) : UserService {

  override fun registerUser(userDto: UserDto): User {
    val user = User(
      username = userDto.username,
      email = userDto.email,
      password = passwordEncoder.encode(userDto.password),
      fullName = userDto.fullName,
      role = userDto.role
    )
    return userRepository.save(user)
  }

  override fun findUserByUsername(username: String): User? {
    return userRepository.findByUsername(username)
  }

  override fun updateUserProfile(userDto: UserDto): User {
    val updatedUser = findUserByUsername(userDto.username)?.let { user ->
      user.email = userDto.email
      if (userDto.password.isNotEmpty()) {
        user.password = passwordEncoder.encode(userDto.password)
      }
      userRepository.save(user)
    } ?: throw IllegalStateException("User not found")

    return updatedUser
  }
}
