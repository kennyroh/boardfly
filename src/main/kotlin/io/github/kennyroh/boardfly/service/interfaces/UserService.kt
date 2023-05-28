package io.github.kennyroh.boardfly.service.interfaces

import io.github.kennyroh.boardfly.dto.UserDto
import io.github.kennyroh.boardfly.entity.User

interface UserService {
  fun registerUser(userDto: UserDto): User
  fun findUserByUsername(username: String): User?
  fun updateUserProfile(userDto: UserDto): User
}
