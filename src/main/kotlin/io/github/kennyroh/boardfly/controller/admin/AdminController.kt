package io.github.kennyroh.boardfly.controller.admin

import io.github.kennyroh.boardfly.dto.UserDto
import io.github.kennyroh.boardfly.entity.UserRole
import io.github.kennyroh.boardfly.service.interfaces.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/admin")
@Validated
class AdminController(private val userService: UserService) {

  @GetMapping("/register")
  fun showRegistrationForm(model: Model): String {
    model.addAttribute("userDto", UserDto("", "", "","", UserRole.ADMIN))
    return "admin/register"
  }

  @PostMapping("/register")
  fun registerAdmin(
    @Valid userDto: UserDto,
    bindingResult: BindingResult
  ): String {
    if (userDto.role != UserRole.ADMIN || bindingResult.hasErrors()) {
      return "admin/register"
    }
    userService.registerUser(userDto)
    return "redirect:/admin/dashboard"
  }
}
