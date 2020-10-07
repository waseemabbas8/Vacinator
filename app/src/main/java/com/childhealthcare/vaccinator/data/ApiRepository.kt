package com.childhealthcare.vaccinator.data

import com.childhealthcare.vaccinator.model.TodoTask


class ApiRepository(private val api: Api) {

    suspend fun login(userName: String, password: String) = api.login(userName, password)

    suspend fun getChildrenList(ucId: Int, mohId: Int) = api.getChildrenList(ucId, mohId)

    suspend fun getMohallahs(ucId: Int) = api.getMohallahs(ucId)

    suspend fun getChildDetails(childId: Int) = api.getChildDetails(childId)

    suspend fun addVaccination(childId: Int, vaccinatorId: Int) =
        api.addVaccination(childId, vaccinatorId)

    suspend fun getTasksList(vaccinatorId: Int) = api.getTasksList(vaccinatorId)

    suspend fun addVaccinatorTask(task: TodoTask) = api.addVaccinatorTask(task)

    suspend fun getQueriesByCouncilId(ucId: Int) = api.getQueriesByCouncilId(ucId)
}