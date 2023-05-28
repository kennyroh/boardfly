package io.github.kennyroh.boardfly.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "post")
class Post(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(nullable = false)
  val title: String,

  @Column(nullable = false, columnDefinition = "TEXT")
  val content: String,

  @Column(nullable = false)
  val createdAt: Date,

  @ManyToOne
  @JoinColumn(name = "user_id")
  val user: User,

  @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
  val comments: MutableList<Comment> = ArrayList()
)
