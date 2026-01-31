package com.example.taskmanager.controller

import com.example.taskmanager.entity.Tag
import com.example.taskmanager.repository.TagRepository
import com.example.taskmanager.repository.TaskRepository
import com.example.taskmanager.repository.UserRepository
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/tags")
class TagController(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository
) {
    @GetMapping("/all")
    fun all(): List<Tag> {
        return tagRepository.findAll()
    }


    @GetMapping
    @Transactional(readOnly = true)
    fun getTagsByTaskId(@RequestParam taskId: Long): List<Tag> {
        val tasks = taskRepository.getTaskById(taskId)
        return tasks.flatMap { it.tags }
    }
}