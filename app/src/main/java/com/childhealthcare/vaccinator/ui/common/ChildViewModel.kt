package com.childhealthcare.vaccinator.ui.common

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.MSG_INTERNET_FAILURE
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.model.User
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class ChildViewModel(
    private val apiRepository: ApiRepository,
    private val user: User,
    private val childId: Int
) : ViewModel() {

    val child: LiveData<Child>
    private val _child = MutableLiveData<Child>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        child = _child
        progressbarVisibility = _progressbarVisibility
        generalResponse = _generalResponse

        getChildDetails()
    }

    @WorkerThread
    private fun getChildDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.getChildDetails(childId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _child.postValue(it)
                    }
                }
            } catch (e: Exception) {
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message ?: "ERROR"))
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

    @WorkerThread
    fun addVaccination() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.addVaccination(childId, user.Id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        getChildDetails()
                        _generalResponse.postValue(it)
                    }
                }
            } catch (e: Exception) {
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message ?: "ERROR"))
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}