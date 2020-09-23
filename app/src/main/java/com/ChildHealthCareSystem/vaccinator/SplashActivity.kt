package com.ChildHealthCareSystem.vaccinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ChildHealthCareSystem.vaccinator.ui.home.common.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        gotoHome()

    }

   private fun gotoHome()
   {
       Handler().postDelayed({

           val intent = Intent(this,
               LoginActivity::class.java)
           startActivity(intent)

           finish()
       }, SPLASH_TIME_OUT)
   }

}