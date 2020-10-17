package com.childhealthcare.vaccinator.model


import com.google.gson.annotations.SerializedName

data class Polio(
    @SerializedName("ChildId")
    val childId: Int,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Vaccinator")
    val vaccinator: String
)