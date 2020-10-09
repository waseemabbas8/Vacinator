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
        GridMenu(app.getString(R.string.Personal),R.drawable.ic_user),
        GridMenu(app.getString(R.string.vaccination),R.drawable.ic_vaccine),
        GridMenu(app.getString(R.string.polio),R.drawable.ic_polio),
        GridMenu(app.getString(R.string.job),R.drawable.ic_calendar),
        GridMenu(app.getString(R.string.noti),R.drawable.ic_notifications)
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