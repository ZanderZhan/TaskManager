package com.example.taskmanager.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tags")
data class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val priority: TagPriority = TagPriority.LOW,


    @ManyToMany(mappedBy = "tags")
    val tasks: MutableSet<Task> = mutableSetOf()
)



enum class TagPriority {
    LOW,
    MEDIUM,
    HIGH
}
