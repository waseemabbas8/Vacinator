package com.childhealthcare.vaccinator.model


import com.google.gson.annotations.SerializedName

data class TodoTask(
    @SerializedName("Date")
    val date: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Time")
    val time: String,
    @SerializedName("Vac_Id")
    val vacId: Int
)