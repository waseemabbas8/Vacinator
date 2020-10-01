package com.ChildHealthCareSystem.vaccinator.data

import com.ChildHealthCareSystem.vaccinator.model.Login
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

@GET("GetLoginInfo")
 suspend fun login(
 @Query("User") user:String,
 @Query("Password") password:String
): Response<Login>

}