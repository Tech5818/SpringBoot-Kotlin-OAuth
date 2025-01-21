package com.example.springbootoauth.config.auth.dto

import com.example.springbootoauth.entity.Member
import java.io.Serializable

data class SessionUser (
    val name: String,
    val email: String,
    val picture: String
): Serializable {}
