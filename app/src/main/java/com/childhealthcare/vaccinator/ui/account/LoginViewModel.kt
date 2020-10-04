package com.childhealthcare.vaccinator.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.PrefRepository
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_OK
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: ApiRepository,
    private val prefRepository: PrefRepository
) : ViewModel() {

    fun login(userName: String, password: String): LiveData<GeneralResponse> {
        val result = MutableLiveData<GeneralResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    repository.login(userName, password)
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.code == RESPONSE_CODE_OK)
                            prefRepository.saveUser(it.data)
                        result.postValue(GeneralResponse(it.code, it.message))
                    }
                }
            } catch (e: Exception) {
                result.postValue(GeneralResponse(RESPONSE_CODE_ERROR, e.message.toString()))
            } catch (t: Throwable) {
                result.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message.toString()))
            }
        }

        return result
    }
}