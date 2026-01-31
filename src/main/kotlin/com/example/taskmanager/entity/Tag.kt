package com.example.taskmanager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
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


    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    val tasks: MutableSet<Task> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Tag) return false
        if (id == 0L || other.id == 0L) return false
        return id == other.id
    }

    override fun hashCode(): Int = if (id == 0L) {
        System.identityHashCode(this)
    } else {
        id.hashCode()
    }
}



enum class TagPriority {
    LOW,
    MEDIUM,
    HIGH
}
