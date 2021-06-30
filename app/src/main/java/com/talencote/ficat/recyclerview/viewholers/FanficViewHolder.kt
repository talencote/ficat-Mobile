package com.talencote.ficat.recyclerview.viewholers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.talencote.ficat.R
import com.talencote.ficat.data.dto.FanficDto

class FanficViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val fanficName : TextView  = itemView.findViewById(R.id.fanfic_name)
    private val fanficTags : TextView = itemView.findViewById(R.id.fanfic_tags)
    private val fanficFandom : TextView = itemView.findViewById(R.id.fanfic_fandom)
    private val fanficAuthor : TextView = itemView.findViewById(R.id.fanfic_author)

    fun onBind(fanfic: FanficDto) {
        fanficName.text = fanfic.name
        fanficTags.text = fanfic.tags.toString().replace("[", "").replace("]", "")
        fanficFandom.text = fanfic.fandom
        fanficAuthor.text = fanfic.author
    }
}