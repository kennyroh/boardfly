package io.github.kennyroh.boardfly.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(nullable = false, columnDefinition = "TEXT")
  val content: String,

  @Column(nullable = false)
  val createdAt: Date,

  @ManyToOne
  @JoinColumn(name = "user_id")
  val user: User,

  @ManyToOne
  @JoinColumn(name = "post_id")
  val post: Post
)
