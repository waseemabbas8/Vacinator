package com.childhealthcare.vaccinator.ui.message

import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.childhealthcare.vaccinator.data.ApiRepository
import com.childhealthcare.vaccinator.model.Mohallah
import com.childhealthcare.vaccinator.model.ParentModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel(private val repository: ApiRepository, private val ucId: Int) : ViewModel() {

    val messageBody = MutableLiveData<String>()

    val progressbarVisibility: LiveData<Int>
    private val _progressbarVisibility = MutableLiveData<Int>()

    val mohallahs: LiveData<List<Mohallah>>
    private val _mohallahs = MutableLiveData<List<Mohallah>>()

    val parents: LiveData<List<ParentModel>>
    private val _parents = MutableLiveData<List<ParentModel>>()

    init {
        progressbarVisibility = _progressbarVisibility
        mohallahs = _mohallahs
        val tempList = listOf(Mohallah(0, "Select Mohallah"))
        _mohallahs.value = tempList

        parents = _parents
        _parents.value = emptyList()

        getMohallahsList()
    }

    @WorkerThread
    private fun getMohallahsList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
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
            _progressbarVisibility.postValue(View.GONE)
        }
    }

    fun getParentsList(mohId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _progressbarVisibility.postValue(View.VISIBLE)
            try {
                val response = repository.getParentsByMoh(mohId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _parents.postValue(it.data)
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
    fun sendMessage(view: View) {
        val msg = messageBody.value
        if (msg.isNullOrEmpty()) {
            makeText(view.context, "Message body should not be empty", Toast.LENGTH_SHORT).show()
            return
        }
        val contacts = parents.value
        if (contacts.isNullOrEmpty()) {
            makeText(view.context, "No recipients found.", Toast.LENGTH_SHORT).show()
            return
        }
        Snackbar.make(view, "Sending your messages in background.", Snackbar.LENGTH_SHORT).show()
        var error = ""
        viewModelScope.launch {
            val smsManager = SmsManager.getDefault()
            for (contact in contacts) {
                try {
                    smsManager.sendTextMessage(contact.number, null, msg, null, null)
                } catch (e: Exception) {
                    error = if (e is SecurityException)
                        "Please Allow SMS Sending permission from settings first."
                    else
                        "Some messages not sent"
                }
            }
        }

        if (error.isNotEmpty())
            Snackbar.make(view, error, Snackbar.LENGTH_SHORT).show()

    }

}