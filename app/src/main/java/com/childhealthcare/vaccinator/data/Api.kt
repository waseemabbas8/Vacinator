package com.childhealthcare.vaccinator.data

import com.childhealthcare.vaccinator.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("GetLoginInfo")
    suspend fun login(
     @Query("User") user: String,
     @Query("Password") password: String
    ): Response<User>

}