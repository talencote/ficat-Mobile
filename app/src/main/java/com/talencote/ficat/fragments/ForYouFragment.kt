package com.talencote.ficat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.talencote.ficat.R
import com.talencote.ficat.viewmodels.ForYouViewModel

class ForYouFragment : Fragment(R.layout.fragment_foryou) {

    private lateinit var forYouViewModel: ForYouViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        forYouViewModel =
                ViewModelProvider(this).get(ForYouViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_foryou, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        forYouViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}