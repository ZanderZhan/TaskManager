package com.example.taskmanager.repository

import com.example.taskmanager.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByNameContaining(name: String): List<User>
}
