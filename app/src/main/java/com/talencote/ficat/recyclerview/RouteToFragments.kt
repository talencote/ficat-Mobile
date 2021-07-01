package com.talencote.ficat.recyclerview

import com.talencote.ficat.data.dto.FanficDto

interface RouteToFragments {

    fun onFanficSelected(fanfic: FanficDto)

    fun readFanfic(id: Int)
}