package com.childhealthcare.vaccinator.ui.account

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.childhealthcare.vaccinator.data.*
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

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
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                result.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                result.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message.toString()))
            }
        }

        return result
    }
}