package io.github.kennyroh.boardfly.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 * User authentication and management
 * Post management
 * Comments on posts
 * File upload for posts
 * Post recommendation
 */
@Controller
class HomeController {

  @GetMapping("/")
  fun home(model: Model): String {
    model.addAttribute("message", "Welcome to the homepage!")
    return "home"
  }
  
}
