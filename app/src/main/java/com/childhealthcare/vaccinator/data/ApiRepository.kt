package com.childhealthcare.vaccinator.data

import retrofit2.http.Query

class ApiRepository(private val api: Api) {

    suspend fun login(userName: String, password: String) = api.login(userName, password)

    suspend fun getChildrenList(ucId: Int, mohId: Int) = api.getChildrenList(ucId, mohId)

    suspend fun getMohallahs(ucId: Int) = api.getMohallahs(ucId)
}