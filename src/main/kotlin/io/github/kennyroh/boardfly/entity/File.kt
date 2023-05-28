package io.github.kennyroh.boardfly.entity

import javax.persistence.*

@Entity
@Table(name = "file")
class File(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,

  @Column(nullable = false)
  val fileName: String,

  @Column(nullable = false)
  val fileType: String,

  @Column(nullable = false)
  val fileUrl: String,

  @ManyToOne
  @JoinColumn(name = "post_id")
  val post: Post
)

