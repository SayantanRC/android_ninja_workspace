package designstring.androidninja.ui.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import designstring.androidninja.data.Repository
import designstring.androidninja.domain.mappers.NetworkEntityMapper
import designstring.androidninja.domain.models.DomainEntity
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val networkEntityMapper: NetworkEntityMapper,
    private val repository: Repository,
): ViewModel() {

    private var pageNumber: Int = 1

    private val peopleList = ArrayList<DomainEntity>(0)

    private var _listOfPeopleLiveData = MutableLiveData<List<DomainEntity>>()
    var listOfPeopleLiveData: LiveData<List<DomainEntity>> = _listOfPeopleLiveData

    suspend fun getNextSetOfPeople() {
        val newList = repository.getPeopleDataFromNetwork(pageNumber++).let {
            networkEntityMapper.toDomainEntityList(it)
        }
        peopleList.addAll(newList)
        _listOfPeopleLiveData.value = peopleList
    }

    suspend fun initPeopleSet(){
        pageNumber = 1
        getNextSetOfPeople()
    }

}