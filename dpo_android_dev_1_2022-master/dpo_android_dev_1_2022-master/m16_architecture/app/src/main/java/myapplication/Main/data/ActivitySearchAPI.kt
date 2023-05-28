package myapplication.Main.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.boredapi.com"

object ActivitySearchAPI {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val searchApi: SearchApi = retrofit.create(SearchApi::class.java)
}



interface SearchApi {
    @GET("/api/activity/")
    suspend fun getActivity(): UsefulActivityDto
}
