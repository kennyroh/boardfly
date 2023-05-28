package io.github.kennyroh.boardfly.dto

import io.github.kennyroh.boardfly.entity.UserRole
import java.io.Serializable

/**
 * A DTO for the {@link io.github.kennyroh.boardfly.entity.User} entity
 */
data class UserDto2(
    val id: Long = 0,
    val username: String? = null,
    val email: String? = null,
    val fullName: String? = null,
    val role: UserRole? = null
) : Serializable
