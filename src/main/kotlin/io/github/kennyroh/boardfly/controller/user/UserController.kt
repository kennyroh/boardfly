package io.github.kennyroh.boardfly.controller.user

import io.github.kennyroh.boardfly.dto.UserDto
import io.github.kennyroh.boardfly.entity.UserRole
import io.github.kennyroh.boardfly.service.interfaces.UserService
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/user")
@Validated
class UserController(private val userService: UserService) {

  @GetMapping("/register")
  fun showRegistrationForm(model: Model): String {
    model.addAttribute("userDto", UserDto("", "", "", "", UserRole.USER))
    model.addAttribute("username", "")
    return "user/register"
  }

  @PostMapping("/register")
  fun registerUser(
    @Valid userDto: UserDto,
    bindingResult: BindingResult,
    model: Model
  ): String {
    userService.findUserByUsername(userDto.username)?.let {
      bindingResult.rejectValue("username", "error.username", "Username is already taken.")
    }
    if (bindingResult.hasErrors()) {
      model.addAttribute("registrationError", "Registration failed.")
      model.addAttribute("username", userDto.username)
      return "user/register"
    }
    userService.registerUser(userDto)
    return "redirect:/user/login"
  }

  @GetMapping("/login")
  fun login(): String {
    return "user/login"
  }

  @GetMapping("/profile")
  fun userProfile(model: Model): String {
    val authentication = SecurityContextHolder.getContext().authentication

    if (authentication == null || authentication is AnonymousAuthenticationToken) {
      return "redirect:/user/login"
    }

    val user = userService.findUserByUsername(authentication.name)
    model.addAttribute("user", user)
    model.addAttribute("userDto", UserDto(user?.username ?: "", user?.email, "", user?.fullName, user?.role ?: UserRole.USER))

    return "user/profile"
  }

  @PostMapping("/profile")
  fun updateUserProfile(
    @Valid userDto: UserDto,
    bindingResult: BindingResult
  ): String {
    if (bindingResult.hasErrors()) {
      return "user/profile"
    }
    userService.updateUserProfile(userDto)
    return "redirect:/user/profile"
  }
}
