package io.github.kennyroh.boardfly.entity

import javax.persistence.*

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true, length = 100)
    val username: String,

    @Column(nullable = true, unique = false)
    var email: String?,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = true)
    val fullName: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: UserRole
)

enum class UserRole {
    ADMIN,
    USER
}
