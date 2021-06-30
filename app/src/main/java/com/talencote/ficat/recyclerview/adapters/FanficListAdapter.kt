package com.talencote.ficat.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.talencote.ficat.R
import com.talencote.ficat.data.dto.FanficDto
import com.talencote.ficat.recyclerview.RouteToFanfic
import com.talencote.ficat.recyclerview.viewholers.FanficViewHolder

class FanficListAdapter(
    private val clickListener: RouteToFanfic
) : RecyclerView.Adapter<FanficViewHolder>() {

    private var fanficList: List<FanficDto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FanficViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_fanfic, parent, false)
        return FanficViewHolder(view)
    }

    override fun onBindViewHolder(holder: FanficViewHolder, position: Int) {
        holder.onBind(fanficList[position])
        holder.itemView.setOnClickListener {
            clickListener.onFanficSelected()
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