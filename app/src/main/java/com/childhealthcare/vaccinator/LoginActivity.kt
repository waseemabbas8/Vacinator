package com.childhealthcare.vaccinator

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.childhealthcare.vaccinator.data.RESPONSE_CODE_OK
import com.childhealthcare.vaccinator.ui.account.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val mViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            progressbar.visibility = View.VISIBLE
            mViewModel.login(edt_email_login.text.toString(), edt_password_login.text.toString())
                .observe(this, {
                    if (it == null) return@observe
                    progressbar.visibility = View.GONE
                    if (it.code == RESPONSE_CODE_OK) {
                        gotoHome()
                        return@observe
                    }
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                })
        }

    }

    private fun gotoHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}