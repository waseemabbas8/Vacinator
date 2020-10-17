package com.childhealthcare.vaccinator.ui.schedule

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.MSG_INTERNET_FAILURE
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.model.TodoTask
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class TasksListViewModel(
    private val apiRepository: ApiRepository,
    private val vaccinatorId: Int
) : ViewModel() {

    val tasks: LiveData<List<TodoTask>>
    private val _tasks = MutableLiveData<List<TodoTask>>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        tasks = _tasks
        progressbarVisibility = _progressbarVisibility
        generalResponse = _generalResponse
    }

    fun getTasksList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = apiRepository.getTasksList(vaccinatorId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _tasks.postValue(it.data)
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