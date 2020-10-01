package com.ChildHealthCareSystem.vaccinator.ui.home.common

import androidx.lifecycle.ViewModel
import com.ChildHealthCareSystem.vaccinator.data.PrefRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val prefRepository: PrefRepository) : ViewModel() {




    fun login(user : String,password:String){
        CoroutineScope(Dispatchers.IO).launch {
            try {

            }catch (e:Exception){
                e.printStackTrace()
            }catch (t:Throwable){


            }





        }



    }
}