package com.talencote.ficat.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.talencote.ficat.R
import com.talencote.ficat.api.ApiClient
import com.talencote.ficat.data.dto.MessageResponse
import com.talencote.ficat.data.dto.RegistrationRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentRegistration : Fragment(R.layout.fragment_registration) {

    private var signupBtn: Button? = null;
    private lateinit var apiClient: ApiClient


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiClient = ApiClient();
        signupBtn = view.findViewById(R.id.btn_reg)

        apiClient.getApiService(requireContext()).registration(RegistrationRequest(
                username = view.findViewById<EditText>(R.id.username).text.toString(),
                email = view.findViewById<EditText>(R.id.email).text.toString(),
                password = view.findViewById<EditText>(R.id.password).text.toString()
        )).enqueue(object : Callback<MessageResponse>{
            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "onFailure - $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MessageResponse>, response: Response<MessageResponse>) {
                Toast.makeText(requireContext(), call.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

    companion object {
        fun newInstance() = FragmentRegistration()
    }
}