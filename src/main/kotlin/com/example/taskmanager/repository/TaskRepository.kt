package com.example.taskmanager.repository

import com.example.taskmanager.dto.TaskWithoutTagsDTO
import com.example.taskmanager.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long> {
    fun findByUserId(userId: Long): List<Task>
    fun getTaskById(id: Long): MutableList<Task>
    fun getTasksByTagsId(id: Long): MutableList<Task>

    @Query("""
        SELECT new com.example.taskmanager.dto.TaskWithoutTagsDTO(
            t.id, t.checked, t.description, t.date, t.user.id
        )
        FROM Task t JOIN t.tags tag
        WHERE tag.id = :tagId
    """)
    fun findTasksByTagIdWithoutTags(@Param("tagId") tagId: Long): List<TaskWithoutTagsDTO>
}
