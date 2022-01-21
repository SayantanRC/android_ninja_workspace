package designstring.androidninja.data

import designstring.androidninja.data.network.models.NetworkEntityPeople
import designstring.androidninja.data.network.retrofit.retrofitImpl
import javax.inject.Inject

class Repository
@Inject
constructor()
{

    suspend fun getPeopleDataFromNetwork(pageNo: Int = 1): List<NetworkEntityPeople>{
        return retrofitImpl.queryPeople(
            6, pageNo
        ).peopleList
    }

}