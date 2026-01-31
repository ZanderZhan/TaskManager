package com.example.taskmanager.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val gender: Gender = Gender.UNSPECIFIED,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val tasks: MutableSet<Task> = mutableSetOf()

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        if (id == 0L || other.id == 0L) return false
        return id == other.id
    }

    override fun hashCode(): Int = if (id == 0L) {
        System.identityHashCode(this)
    } else {
        id.hashCode()
    }
}

enum class Gender {
    UNSPECIFIED,
    MALE,
    FEMALE,
}
