package com.childhealthcare.vaccinator.data

import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.model.Mohallah
import com.childhealthcare.vaccinator.model.User
import com.childhealthcare.vaccinator.model.common.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("GetLoginInfo")
    suspend fun login(
     @Query("User") user: String,
     @Query("Password") password: String
    ): Response<BaseResponse<User>>

    @GET("GetChildListByMuhalla")
    suspend fun getChildrenList(
        @Query("ucid") ucId: Int,
        @Query("mid") mohId: Int
    ): Response<List<Child>>

    @GET("GetMuhallasListByUcId")
    suspend fun getMohallahs(
        @Query("ucid") ucId: Int
    ): Response<List<Mohallah>>

    @GET("GetSingleChildDetail")
    suspend fun getChildDetails(
        @Query("id") childId: Int
    ): Response<Child>

}