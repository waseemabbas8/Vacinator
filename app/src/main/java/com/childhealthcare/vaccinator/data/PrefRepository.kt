package com.childhealthcare.vaccinator.data

import android.app.Application
import android.content.Context
import com.childhealthcare.vaccinator.R
import com.childhealthcare.vaccinator.model.GridMenu
import com.childhealthcare.vaccinator.model.User
import com.google.gson.Gson
import java.lang.Exception

private const val KEY_USER_OBJ = "vaccinator_user_object"

class PrefRepository(private val app: Application){

    private val pref = app.getSharedPreferences(APP_TAG, Context.MODE_PRIVATE)

    fun getDashboardItems() : List<GridMenu> = listOf(
        GridMenu(R.string.Personal.toString(),R.drawable.ic_user),
        GridMenu(R.string.vaccination.toString(),R.drawable.ic_vaccine),
        GridMenu(R.string.polio.toString(),R.drawable.ic_polio),
        GridMenu(R.string.child.toString(),R.drawable.ic_child),
        GridMenu(R.string.job.toString(),R.drawable.ic_calendar),
        GridMenu(R.string.noti.toString(),R.drawable.ic_notifications)
    )

    fun saveUser(user: User) {
        val userStr = Gson().toJson(user)
        pref.edit().putString(KEY_USER_OBJ, userStr).apply()
    }

    fun getUser(): User? {
        val userJson = pref.getString(KEY_USER_OBJ, null)
        return try {
            Gson().fromJson(userJson, User::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun deleteUser() {
        pref.edit().remove(KEY_USER_OBJ).apply()
    }

}