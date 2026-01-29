package com.example.taskmanager.controller

import com.example.taskmanager.model.User
import com.example.taskmanager.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    // GET all users
    @GetMapping
    fun all(): List<User> {
        return userRepository.findAll()
    }

    // GET user by id
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    // POST create user
    @PostMapping
    fun create(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    // PUT update user
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody user: User): User {
        return userRepository.save(user.copy(id = id))
    }

    // DELETE user
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        userRepository.deleteById(id)
    }
}