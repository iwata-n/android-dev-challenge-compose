package com.example.androiddevchallenge.model

data class Animal(
    val id: Id,
    val name: String,
    val age: Int,
    val sex: Sex = Sex.UNKNOWN,
    val image: String,
    val description: String,
    val mother: Id? = null,
    val father: Id? = null,
)

data class Id(val value: String) {
    override fun toString(): String {
        return value
    }
}

enum class Sex {
    MALE,
    FEMALE,
    UNKNOWN
}