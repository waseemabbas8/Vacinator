package com.childhealthcare.vaccinator.model

data class Mohallah(
    val id: Int,
    val name: String
) {
    override fun toString(): String = name
}