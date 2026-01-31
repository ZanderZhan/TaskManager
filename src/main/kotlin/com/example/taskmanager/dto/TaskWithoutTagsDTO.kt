package com.example.taskmanager.dto

data class TaskWithoutTagsDTO(
    val id: Long,
    val checked: Boolean,
    val description: String,
    val date: Long,
    val userId: Long?
)
