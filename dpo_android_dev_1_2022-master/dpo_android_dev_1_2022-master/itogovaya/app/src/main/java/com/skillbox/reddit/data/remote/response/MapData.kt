package com.skillbox.reddit.data.remote.response

import com.skillbox.reddit.domain.model.AbstractRedditEntity

interface MapData {
    
    fun map(): AbstractRedditEntity
}