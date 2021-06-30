package com.talencote.ficat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.talencote.ficat.R

class FragmentFanficDetails : Fragment(R.layout.fragment_fanfic_details) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    companion object{
        fun newInstance() =  FragmentFanficDetails()
    }
}