package com.example.springbootoauth.entity

import com.example.springbootoauth.dto.UserDTO
import com.example.springbootoauth.entity.enums.Role
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long? = null,

    @NotNull
    var name: String,

    @NotNull
    val email: String,

    @Column
    var picture: String,

    @Enumerated(EnumType.STRING)
    @NotNull
    val role: Role,
) {
    fun update(name: String, picture: String): Member {
        this.name = name
        this.picture = picture

        return this
    }

    fun getRoleKey(): String {
        return this.role.key
    }
}

fun Member.toDTO(): UserDTO {
    return UserDTO(id!!, name, email, picture, role)
}