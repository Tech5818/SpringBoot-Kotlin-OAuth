package com.example.springbootoauth.controller

import com.example.springbootoauth.config.auth.dto.SessionUser
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class HomeController(
    private val httpSession: HttpSession,
) {
    @GetMapping("/")
    fun home(model: Model): String {
        val user = httpSession.getAttribute("user") as SessionUser?

        if (user != null) {
            model.addAttribute("userName", user.name);
        }

        return "index";
    }
}