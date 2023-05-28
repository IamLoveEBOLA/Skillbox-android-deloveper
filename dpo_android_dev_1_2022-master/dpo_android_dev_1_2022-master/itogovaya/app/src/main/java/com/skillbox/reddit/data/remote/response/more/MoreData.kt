package com.skillbox.reddit.data.remote.response.more

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.skillbox.reddit.data.remote.response.Thing

@JsonClass(generateAdapter = true)
data class MoreData(
    @Json(name = "kind")
    override val kind: String,
    @Json(name = "data")
    override val data: MoreDto
): Thing