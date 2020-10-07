package com.childhealthcare.vaccinator.ui.common

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.model.Child
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ChildViewModel(private val apiRepository: ApiRepository, private val childId: Int) : ViewModel() {

    val child: LiveData<Child>
    private val _child = MutableLiveData<Child>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()
    init {
        child = _child
        progressbarVisibility = _progressbarVisibility

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
                e.printStackTrace()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}