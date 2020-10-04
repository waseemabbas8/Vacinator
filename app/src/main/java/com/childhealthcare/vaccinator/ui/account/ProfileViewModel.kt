package com.childhealthcare.vaccinator.ui.account

import androidx.lifecycle.ViewModel
import com.childhealthcare.vaccinator.data.PrefRepository

class ProfileViewModel(prefRepository: PrefRepository): ViewModel() {

    val user = prefRepository.getUser()

}