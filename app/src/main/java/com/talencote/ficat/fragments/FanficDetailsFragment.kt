package com.talencote.ficat.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.talencote.ficat.R
import com.talencote.ficat.data.dto.FanficDto
import com.talencote.ficat.recyclerview.RouteToFragments

class FanficDetailsFragment : Fragment(R.layout.fragment_fanfic_details) {

    private var clickListener: RouteToFragments? = null

    private lateinit var fanficName : TextView
    private lateinit var fanficTags : TextView
    private lateinit var fanficDescription : TextView
    private lateinit var fanficFandom : TextView
    private lateinit var fanficAuthor : TextView
    private lateinit var readBtn : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        fanficName = view.findViewById(R.id.fanfic_name)
        fanficTags = view.findViewById(R.id.fanfic_tags)
        fanficDescription = view.findViewById(R.id.fanfic_description)
        fanficFandom = view.findViewById(R.id.fanfic_fandom)
        fanficAuthor = view.findViewById(R.id.fanfic_author)
        readBtn = view.findViewById(R.id.btn_read)

        val fanfic = arguments?.getParcelable<FanficDto>(FANFIC_KEY)
        fillViews(fanfic)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun fillViews(fanfic : FanficDto?) {
        fanficName.text = fanfic!!.name
        fanficTags.text = fanfic.tags.toString().replace("[", "").replace("]", "")
        fanficDescription.text = fanfic.description
        fanficFandom.text = fanfic.fandom
        fanficAuthor.text = fanfic.author
        clickListener = context as RouteToFragments
        readBtn.setOnClickListener {
            clickListener!!.readFanfic(fanfic.id)
        }
    }


    companion object{
        fun newInstance(fanfic : FanficDto) : FanficDetailsFragment {
            val fragment = FanficDetailsFragment()
            val args = Bundle()
            args.putParcelable(FANFIC_KEY, fanfic)
            fragment.arguments = args
            return fragment
        }

        private const val FANFIC_KEY = "FANFIC_KEY"
    }
}