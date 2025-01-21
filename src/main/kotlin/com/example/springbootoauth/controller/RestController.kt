package com.example.springbootoauth.controller

import com.example.springbootoauth.config.auth.dto.SessionUser
import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/rest")
class RestController(
    val httpSession: HttpSession,
) {
    @GetMapping("/")
    fun getUser(): ResponseEntity<SessionUser> {
        val user = httpSession.getAttribute("user") as SessionUser?

        user?.let {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(it)
        } ?: run {
            throw ResponseStatusException(HttpStatus.FORBIDDEN)
        }
    }
}