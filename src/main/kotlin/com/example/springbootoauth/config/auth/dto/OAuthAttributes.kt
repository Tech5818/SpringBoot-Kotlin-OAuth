package com.example.springbootoauth.config.auth.dto

import com.example.springbootoauth.entity.Member
import com.example.springbootoauth.entity.enums.Role

data class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    companion object {
        fun of(registrationId: String, userAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
//            if (registrationId.contentEquals("google")) {
//                return ofGoogle(userAttributeName, attributes)
//            }
            return ofGoogle(userAttributeName, attributes)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
            )
        }
    }
    fun toEntity(): Member {
        return Member(name = name,
            email = email,
            picture = picture,
            role = Role.USER
        )
    }
}

