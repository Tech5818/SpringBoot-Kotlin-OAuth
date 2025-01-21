package com.example.springbootoauth.dto

import com.example.springbootoauth.entity.Member
import com.example.springbootoauth.entity.enums.Role

data class UserDTO(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
    val role: Role
) {
    fun toEntity() :Member {
        return Member(id,name,email,picture,role)
    }
}
