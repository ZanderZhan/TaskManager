package com.example.taskmanager.model

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
    val gender: Gender = Gender.UNSPECIFIED
)

enum class Gender {
    UNSPECIFIED,
    MALE,
    FEMALE,
}