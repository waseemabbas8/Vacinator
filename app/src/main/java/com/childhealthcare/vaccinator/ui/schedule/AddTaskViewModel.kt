package com.childhealthcare.vaccinator.ui.schedule

import android.util.Log
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
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class AddTaskViewModel(
    private val repository: ApiRepository,
    private val userId: Int
) : ViewModel() {

    val description = MutableLiveData<String>()

    val year = MutableLiveData<Int>()
    val month = MutableLiveData<Int>()
    val day = MutableLiveData<Int>()

    val hours = MutableLiveData<Int>()
    val minutes = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        generalResponse = _generalResponse

        val c = Calendar.getInstance()
        year.value = c.get(Calendar.YEAR)
        month.value = c.get(Calendar.MONTH)
        day.value = c.get(Calendar.DATE)

        hours.value = c.get(Calendar.HOUR)
        minutes.value = c.get(Calendar.MINUTE)

    }

    @WorkerThread
    fun addTask() {
        viewModelScope.launch {
            _generalResponse.postValue(null)
            try {
                val date = "${day.value}-${month.value?.plus(1)}-${year.value}"
                val time = "${hours.value}:${minutes.value}"
                Log.d("DateTime", date)
                Log.d("DateTime", time)
                if (description.value.isNullOrEmpty()){
                    _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, "Please add some description."))
                    return@launch
                }
                val task = TodoTask(date, description.value.toString(), 0, time, userId)
                val response = repository.addVaccinatorTask(task)
                if (response.isSuccessful){
                    response.body()?.let {
                        _generalResponse.postValue(it)
                    }
                }

            } catch (e: Exception) {
                val msg = if (e is IOException) MSG_INTERNET_FAILURE else e.message.toString()
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, msg))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message.toString()))
            }
        }
    }

}