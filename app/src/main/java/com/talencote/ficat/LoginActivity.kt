package com.talencote.ficat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talencote.ficat.fragments.FragmentLogin

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.persistent_container, FragmentLogin.newInstance(), FragmentLogin::class.java.simpleName)
            commit()
        }
    }
}