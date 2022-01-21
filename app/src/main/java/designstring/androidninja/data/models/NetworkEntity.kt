package designstring.androidninja.data.models

import com.google.gson.annotations.SerializedName

data class NetworkEntity(
    @SerializedName("data")
    val peopleList: List<NetworkEntityPeople>,

    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)