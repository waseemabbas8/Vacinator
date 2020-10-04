package com.childhealthcare.vaccinator.model

data class Child(
    val Id: Int,
    val Name: String,
    val lastVaccinationDate: String
) {
    override fun toString(): String = "$Name, $lastVaccinationDate"
}