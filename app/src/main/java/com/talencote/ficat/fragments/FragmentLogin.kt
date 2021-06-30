package com.talencote.ficat.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.talencote.ficat.LoginActivity
import com.talencote.ficat.MainActivity
import com.talencote.ficat.R
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.data.dto.LoginRequest
import com.talencote.ficat.data.dto.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentLogin : Fragment(R.layout.fragment_login) {

    private var signupBtn: Button? = null;
    private var signinBtn: Button? = null;
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        apiClient = ApiClient()
        sessionManager = SessionManager(requireActivity())

        signupBtn = view.findViewById(R.id.signup)
        signupBtn?.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.persistent_container, FragmentRegistration.newInstance(), FragmentRegistration::class.java.simpleName)
                addToBackStack("trans: to reg fragm")
                commit()
            }
        }

        signinBtn = view.findViewById(R.id.btn_log)
        signinBtn?.setOnClickListener {

            apiClient.getApiService(requireContext()).login(LoginRequest(
                    username = view.findViewById<EditText>(R.id.username).text.toString(), // "admin2"
                    password = view.findViewById<EditText>(R.id.password).text.toString())) // "password"
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Error logging in
                            Toast.makeText(requireContext(), "LoginFragment - onFailure - $t", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            val loginResponse = response.body()

                            if (loginResponse != null) {
                                sessionManager.saveAuthToken(loginResponse.authToken)
                                Toast.makeText(requireContext(), "Auth success", Toast.LENGTH_SHORT).show()
                                val intent = Intent(requireContext(), MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                    // Error logging in
                                    Toast.makeText(requireContext(), "LoginFragment - onResponse -> error", Toast.LENGTH_SHORT).show()
                            }

                        }
                    })
        }


    }

    companion object {
        fun newInstance() = FragmentLogin()
    }
}