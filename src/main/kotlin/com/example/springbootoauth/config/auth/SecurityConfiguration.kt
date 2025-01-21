package com.example.springbootoauth.config.auth

import com.example.springbootoauth.entity.enums.Role
import com.example.springbootoauth.service.CustomOAuth2UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val customOAuth2UserService: CustomOAuth2UserService
) {
    @Bean
    fun securityFilterChain(http:HttpSecurity) : SecurityFilterChain {
        http
            .csrf {
                it.disable()
            }
            .authorizeHttpRequests {
                it.requestMatchers("/user").hasRole(Role.USER.name)
                .requestMatchers("/", "/styles/**", "/scripts/**").permitAll()
                .anyRequest().authenticated()
            }
            .logout {
                logoutConfig -> logoutConfig.logoutSuccessUrl("/")
            }
            .oauth2Login(Customizer.withDefaults())


        return http.build()
    }
}