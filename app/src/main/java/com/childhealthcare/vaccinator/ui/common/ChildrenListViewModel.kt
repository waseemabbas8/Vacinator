package com.childhealthcare.vaccinator.ui.common

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.PrefRepository
import com.childhealthcare.vaccinator.model.Child
import com.childhealthcare.vaccinator.model.Mohallah
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChildrenListViewModel(
    private val repository: ApiRepository,
    prefRepository: PrefRepository,
    val isVaccine: Boolean
) : ViewModel() {

    val ucId = prefRepository.getUser()?.UC ?: 0
    var mohId = 0

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val searchText = MutableLiveData<String>()

    val children: LiveData<List<Child>>
    private val _children = MutableLiveData<List<Child>>()

    val mohallahs: LiveData<List<Mohallah>>
    private val _mohallahs = MutableLiveData<List<Mohallah>>()

    init {
        progressbarVisibility = _progressbarVisibility
        children = _children
        mohallahs = _mohallahs
        _children.value = emptyList()
        val tempList = listOf(Mohallah(0, "All"))
        _mohallahs.value = tempList

        getChildrenList()
        getMohallahsList()
    }

    @WorkerThread
    fun getChildrenList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = repository.getChildrenList(ucId, mohId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _children.postValue(it)
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

    @WorkerThread
    private fun getMohallahsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getMohallahs(ucId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val tempList = arrayListOf<Mohallah>()
                        tempList.add(Mohallah(0, "All"))
                        tempList.addAll(it)
                        _mohallahs.postValue(tempList)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }

}