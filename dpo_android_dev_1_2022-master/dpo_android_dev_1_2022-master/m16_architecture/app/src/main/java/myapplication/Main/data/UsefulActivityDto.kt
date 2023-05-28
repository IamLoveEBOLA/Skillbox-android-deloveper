package myapplication.Main.data


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import myapplication.Main.entity.UsefulActivity
import javax.inject.Inject

@JsonClass(generateAdapter = true)

class UsefulActivityDto @Inject constructor(

    @Json(name = "activity") override val activity: String ,
    @Json(name = "type") override val type: String ,
    @Json(name = "participants") override val participants: Int ,
    @Json(name = "price") override val price: Float ,
    @Json(name = "key") override val key: String ,
    @Json(name = "accessibility") override val accessibility: Float

) : UsefulActivity
