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
import com.talencote.ficat.viewmodels.FanficContentViewModel

class FanficContentFragment: Fragment(R.layout.fragment_fanfic_content) {

    private lateinit var viewModel: FanficContentViewModel
    private lateinit var contentView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(FanficContentViewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contentView = view.findViewById(R.id.fanfic_content)
    }

    override fun onStart() {
        super.onStart()

        val id = arguments?.getInt(ID_KEY)
        if (id != null) {
            viewModel.find(id)
        }
        viewModel.data.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                contentView.text = it.content
            }
        })
    }


    companion object{
        fun newInstance(id: Int): FanficContentFragment {
            val fragment = FanficContentFragment()
            val args = Bundle()
            args.putInt(ID_KEY, id)
            fragment.arguments = args
            return fragment
        }

        private val ID_KEY = "ID_KEY"
    }
}