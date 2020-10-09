package com.childhealthcare.vaccinator.ui.query

import android.view.View
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_ERROR
import com.childhealthcare.vaccinator.model.QueryModel
import com.childhealthcare.vaccinator.model.common.GeneralResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QueriesListViewModel(
    private val repository: ApiRepository,
    private val ucId: Int
) : ViewModel() {

    val queries: LiveData<List<QueryModel>>
    private val _queries = MutableLiveData<List<QueryModel>>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val generalResponse: LiveData<GeneralResponse>
    private val _generalResponse = MutableLiveData<GeneralResponse>()

    init {
        queries = _queries
        progressbarVisibility = _progressbarVisibility
        generalResponse = _generalResponse

        getQueriesList()
    }

    @WorkerThread
    private fun getQueriesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = repository.getQueriesByCouncilId(ucId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _queries.postValue(it.data)
                    }
                }
            } catch (e: Exception) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, e.message ?: "ERROR"))
            } catch (t: Throwable) {
                _generalResponse.postValue(GeneralResponse(RESPONSE_CODE_ERROR, t.message ?: "ERROR"))
            }
            _progressbarVisibility.postValue(View.GONE)
        }
    }

}