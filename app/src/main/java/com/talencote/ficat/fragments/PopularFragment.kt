package com.talencote.ficat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talencote.ficat.R
import com.talencote.ficat.recyclerview.adapters.FanficListAdapter
import com.talencote.ficat.viewmodels.PopularViewModel

class PopularFragment : Fragment(R.layout.fragment_popular) {

    private lateinit var popularViewModel: PopularViewModel
    private var recycler: RecyclerView? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycler = view.findViewById(R.id.rv_fanfics)

        recycler?.adapter = FanficListAdapter(requireContext())
        val layoutManager = GridLayoutManager(context, 2)
        recycler?.layoutManager = layoutManager

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        popularViewModel.data.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                (recycler?.adapter as? FanficListAdapter)?.apply {
                    bindFanfics(it)
                }
            }
        })
    }
}