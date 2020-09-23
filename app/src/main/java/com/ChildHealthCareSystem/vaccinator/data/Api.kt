package com.ChildHealthCareSystem.vaccinator.data

import retrofit2.http.GET

interface Api {
@GET("GetLoginInfo")
 fun login()
}