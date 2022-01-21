package designstring.androidninja.data.retrofit

import designstring.androidninja.data.models.NetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("users")
    suspend fun queryPeople(
        @Query("per_page") perPage: Int,
        @Query("page") pageNo: Int,
    ): NetworkEntity

}