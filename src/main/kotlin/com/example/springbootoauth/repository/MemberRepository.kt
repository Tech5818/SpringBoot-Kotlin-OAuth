package com.example.springbootoauth.repository

import com.example.springbootoauth.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}