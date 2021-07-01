package com.talencote.ficat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talencote.ficat.fragments.LoginFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.persistent_container, LoginFragment.newInstance(), LoginFragment::class.java.simpleName)
            commit()
        }
    }
}