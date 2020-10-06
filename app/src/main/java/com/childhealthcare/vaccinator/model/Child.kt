package com.childhealthcare.vaccinator.model

import com.google.gson.annotations.SerializedName

data class Child(
    val Id: Int,
    val Name: String,
    val LastDate: String,
    @SerializedName("polio_status")
    val polioStatus: Boolean
) {
    override fun toString(): String = "$Name, $LastDate"
}