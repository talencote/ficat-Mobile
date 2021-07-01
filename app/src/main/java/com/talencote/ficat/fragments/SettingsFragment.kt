package com.talencote.ficat.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.talencote.ficat.LoginActivity
import com.talencote.ficat.R
import com.talencote.ficat.api.SessionManager
import com.talencote.ficat.viewmodels.SettingsViewModel

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var logoutBtn: Button? = null
    private var setFandomsBtn: Button? = null
    private var fandomsString: EditText? = null
    private lateinit var settingsViewModel: SettingsViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        sessionManager = SessionManager(requireContext())
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logoutBtn = view.findViewById(R.id.btn_logout)
        setFandomsBtn = view.findViewById(R.id.btn_set_fandoms)
        fandomsString = view.findViewById(R.id.fandoms_list)

        settingsViewModel.data.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                fandomsString?.setText(it)
            }
        })

        setFandomsBtn?.setOnClickListener {
            settingsViewModel.setFandoms(fandomsString?.text.toString())
        }

        logoutBtn?.setOnClickListener {
            sessionManager.clearData()
            Toast.makeText(requireContext(), "Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}