package com.childhealthcare.vaccinator.model

import com.google.gson.annotations.SerializedName

data class Child(
    @SerializedName("Council")
    val council: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("DOB")
    val dob: String,
    @SerializedName("Father_CNIC")
    val fatherCNIC: String,
    @SerializedName("Father_Name")
    val fatherName: String,
    @SerializedName("Gen_ID")
    val genId: Int,
    @SerializedName("Gender")
    val gender: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("LastDate")
    val lastDate: String,
    @SerializedName("MID")
    val mohId: Int,
    @SerializedName("Muhalla")
    val mohName: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("polio_status")
    val polioStatus: Boolean,
    @SerializedName("UCID")
    val ucId: Int,
    @SerializedName("vaccinations")
    val vaccinations: List<Vaccination>,
    @SerializedName("polios")
    val polioList: List<Polio>
) {
    override fun toString(): String = "$name, $fatherName, $dob, $mohName"

    fun getUpcomingDate() = try {
        vaccinations.last().nextDate
    } catch (e: Exception) {
        ""
    }

    fun getPolioCountStr() = "(${polioList.count()}/20)"

    fun getPolioProgress() = polioList.count()/20 * 100

}