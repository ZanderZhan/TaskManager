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


    @ManyToMany(mappedBy = "users")
    val tasks: MutableSet<Task> = mutableSetOf()

)

enum class Gender {
    UNSPECIFIED,
    MALE,
    FEMALE,
}