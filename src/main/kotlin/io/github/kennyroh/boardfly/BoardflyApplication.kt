package io.github.kennyroh.boardfly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BoardflyApplication

fun main(args: Array<String>) {
    runApplication<BoardflyApplication>(*args)
}
