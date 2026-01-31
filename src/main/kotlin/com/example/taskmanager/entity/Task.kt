package com.example.taskmanager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    val user: User? = null,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "task_tags",
        joinColumns = [JoinColumn(name = "task_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: MutableSet<Tag> = mutableSetOf(),

    @Column(nullable = false)
    val checked: Boolean = false,

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false)
    val date: Long = 0

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Task) return false
        if (id == 0L || other.id == 0L) return false
        return id == other.id
    }

    override fun hashCode(): Int = if (id == 0L) {
        System.identityHashCode(this)
    } else {
        id.hashCode()
    }
}
