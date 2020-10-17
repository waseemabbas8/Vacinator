package com.childhealthcare.vaccinator.model


import com.google.gson.annotations.SerializedName

data class ParentModel(
    @SerializedName("Muhalla")
    val moh: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Number")
    val number: String
)