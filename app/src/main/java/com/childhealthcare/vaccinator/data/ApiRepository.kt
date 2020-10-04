package com.childhealthcare.vaccinator.data

class ApiRepository(private val api: Api) {

    suspend fun login(userName: String, password: String) = api.login(userName, password)

}