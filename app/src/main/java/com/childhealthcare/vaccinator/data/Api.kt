package com.childhealthcare.vaccinator.data

import com.childhealthcare.vaccinator.model.*
import com.childhealthcare.vaccinator.model.common.BaseResponse
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import retrofit2.Response
import retrofit2.http.*

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

    @POST("AddVaccination")
    suspend fun addVaccination(
        @Query("cid") childId: Int,
        @Query("vid") vaccinatorId: Int
    ): Response<GeneralResponse>

    @POST("UpdatePolioStatus")
    suspend fun addPolio(
        @Query("chilid") childId: Int,
        @Query("vid") vaccinatorId: Int
    ): Response<GeneralResponse>

    @GET("GetVaccinatorTaskList")
    suspend fun getTasksList(
        @Query("vid") vaccinatorId: Int
    ): Response<BaseResponse<List<TodoTask>>>

    @POST("AddVaccinatorTask")
    suspend fun addVaccinatorTask(
        @Body task: TodoTask
    ): Response<GeneralResponse>

    @GET("GetListofQueriesByCouncilId")
    suspend fun getQueriesByCouncilId(
        @Query("ucid") ucId: Int
    ): Response<BaseResponse<List<QueryModel>>>
}