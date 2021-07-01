package com.talencote.ficat.recyclerview.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talencote.ficat.R
import com.talencote.ficat.data.dto.FanficDto
import com.talencote.ficat.recyclerview.RouteToFragments
import com.talencote.ficat.recyclerview.viewholers.FanficViewHolder

class FanficListAdapter(
    private val context: Context
) : RecyclerView.Adapter<FanficViewHolder>() {

    private var fanficList: List<FanficDto> = listOf()
    private var clickListener: RouteToFragments? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanficViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_fanfic, parent, false)
        return FanficViewHolder(view)
    }

    override fun onBindViewHolder(holder: FanficViewHolder, position: Int) {
        holder.onBind(fanficList[position])
        clickListener = context as RouteToFragments
        holder.itemView.setOnClickListener {
            clickListener?.onFanficSelected(fanficList[position])
        }
    }

    override fun getItemCount(): Int {
        return fanficList.size
    }

    fun bindFanfics(newFanfics: List<FanficDto>) {
        fanficList = newFanfics
        notifyDataSetChanged()
    }
}