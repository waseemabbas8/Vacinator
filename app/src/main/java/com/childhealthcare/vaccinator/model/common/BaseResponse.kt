package com.childhealthcare.vaccinator.model.common

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)