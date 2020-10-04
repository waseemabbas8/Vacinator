package com.childhealthcare.vaccinator.ui.home

import androidx.lifecycle.ViewModel
import com.childhealthcare.vaccinator.data.PrefRepository

class VacinatorDashboardViewModel(private val prefRepository: PrefRepository) : ViewModel() {

    val dashboardItems = prefRepository.getDashboardItems()

    val user = prefRepository.getUser()

    fun logoutUser() {
        prefRepository.deleteUser()
    }

}