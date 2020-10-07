package com.childhealthcare.vaccinator.model


import com.google.gson.annotations.SerializedName

data class Vaccination(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Inject")
    val inject: Boolean,
    @SerializedName("Next_Date")
    val nextDate: String,
    @SerializedName("Polio")
    val polio: Boolean,
    @SerializedName("Vaccination_Date")
    val vaccinationDate: String,
    @SerializedName("vaccinator")
    val vaccinator: String
)