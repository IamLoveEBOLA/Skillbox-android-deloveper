package com.skillbox.reddit.data.remote.response.subreddits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.skillbox.reddit.data.remote.response.Thing

@JsonClass(generateAdapter = true)
data class SubredditsData(
    @Json(name = "kind")
    override val kind: String,
    @Json(name = "data")
    override val data: SubredditDto
): Thing