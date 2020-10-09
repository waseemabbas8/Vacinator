package com.childhealthcare.vaccinator.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class QueryModel(
    @SerializedName("CNIC")
    val cNIC: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Mobile")
    val mobile: String,
    @SerializedName("Muhalla")
    val mohName: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Parent_Id")
    val parentId: Int,
    @SerializedName("UC")
    val ucName: String
): Serializable