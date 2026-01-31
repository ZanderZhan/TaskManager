package com.example.taskmanager.controller

import com.example.taskmanager.dto.TaskWithoutTagsDTO
import com.example.taskmanager.entity.Tag
import com.example.taskmanager.entity.TagPriority
import com.example.taskmanager.entity.Task
import com.example.taskmanager.entity.User
import com.example.taskmanager.entity.Gender
import com.example.taskmanager.repository.TaskRepository
import com.example.taskmanager.repository.UserRepository
import com.example.taskmanager.repository.TagRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/task")
class TaskController(
    private val taskRepository: TaskRepository,
    private val userRepository: UserRepository,
    private val tagRepository: TagRepository
) {

    @GetMapping("/all")
    fun all(): List<Task> {
        return taskRepository.findAll()
    }

    @GetMapping("/{userId}")
    fun getTasksByUserId(@PathVariable userId: Long): List<Task> {
        return taskRepository.findByUserId(userId)
    }

    @GetMapping()
    fun getTaskById(@RequestParam id: Long): Task? {
        return taskRepository.findById(id).orElse(null)
    }

    @GetMapping("/by-tag")
    fun getTasksByTag(@RequestParam tagId: Long): List<TaskWithoutTagsDTO> {
        return taskRepository.findTasksByTagIdWithoutTags(tagId)
    }


    @GetMapping("test")
    fun test(): Map<String, Any> = mapOf(
        "message" to "Hello World!"
    )
    
    @GetMapping("test/save")
    fun save(): Map<String, Any> {
        // Create 10 users
        val users = (1..10).map { i ->
            userRepository.save(User(
                name = "User $i",
                gender = Gender.entries[i % 3]
            ))
        }

        // Create 3 tags
        val tags = listOf(
            tagRepository.save(Tag(name = "Work", priority = TagPriority.HIGH)),
            tagRepository.save(Tag(name = "Personal", priority = TagPriority.MEDIUM)),
            tagRepository.save(Tag(name = "Urgent", priority = TagPriority.LOW))
        )

        // Create 300 tasks with random user and tags
        val tasks = (1..300).map { i ->
            val taskUser = users.random()
            val taskTags = tags.shuffled().take((1..3).random()).toMutableSet()
            
            taskRepository.save(Task(
                description = "Task $i - ${listOf("Review", "Complete", "Start", "Plan", "Discuss").random()} project",
                checked = i % 5 == 0,
                date = System.currentTimeMillis() + (i * 86400000L),
                user = taskUser,
                tags = taskTags
            ))
        }

        return mapOf(
            "usersCreated" to users.size,
            "tagsCreated" to tags.size,
            "tasksCreated" to tasks.size
        )
    }

}
