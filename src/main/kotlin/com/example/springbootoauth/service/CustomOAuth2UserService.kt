package com.example.springbootoauth.service

import com.example.springbootoauth.config.auth.dto.OAuthAttributes
import com.example.springbootoauth.config.auth.dto.SessionUser
import com.example.springbootoauth.entity.Member
import com.example.springbootoauth.repository.MemberRepository
import jakarta.servlet.http.HttpSession
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*

@Service

class CustomOAuth2UserService(
    val memberRepository: MemberRepository,
    val session: HttpSession,
    val httpSession: HttpSession,
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)

        val registrationId = userRequest
            .clientRegistration
            .registrationId

        val userNameAttributeName = userRequest
            .clientRegistration
            .providerDetails
            .userInfoEndpoint
            .userNameAttributeName

        val attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)

        val member: Member = saveOrUpdate(attributes)

        httpSession.setAttribute("user", SessionUser(
            member.name,
            member.email,
            member.picture)
        )

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority(member.getRoleKey())),
            attributes.attributes,
            attributes.nameAttributeKey
        )
    }

    private fun saveOrUpdate(attributes: OAuthAttributes): Member {
        val member = memberRepository.findByEmail(attributes.email)
            ?.update(attributes.name, attributes.picture)
            ?: attributes.toEntity()

        return memberRepository.save(member)
    }
}