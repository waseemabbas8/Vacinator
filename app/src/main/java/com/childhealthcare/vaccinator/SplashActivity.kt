package com.childhealthcare.vaccinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import com.childhealthcare.vaccinator.data.PrefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get


private const val SPLASH_TIME_OUT: Long = 3000

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val prefRepository: PrefRepository = get()

        if (prefRepository.getUser() == null)
            gotoLogin()
        else
            gotoHome()

    }

    private fun gotoLogin() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(SPLASH_TIME_OUT)
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun gotoHome() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(SPLASH_TIME_OUT)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}