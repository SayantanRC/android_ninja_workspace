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
class MainViewModel_old
@Inject
constructor(
    private val networkEntityMapper: NetworkEntityMapper,
    private val repository: Repository,
): ViewModel() {

    private var pageNumber: Int = 1

    /**
     * Internal data set.
     */
    private val peopleList = ArrayList<DomainEntity>(0)

    /**
     * Live data which the main activity observes for new data.
     */
    private var _listOfPeopleLiveData = MutableLiveData<Triple<List<DomainEntity>, Int, Int>>()
    var listOfPeopleLiveData: LiveData<Triple<List<DomainEntity>, Int, Int>> = _listOfPeopleLiveData

    /**
     * Function to call [repository] method to load the next set of data.
     * Also uses the [networkEntityMapper] to convert the received data to domain model.
     */
    suspend fun getNextSetOfPeople() {
        val newList = repository.getPeopleDataFromNetwork(pageNumber++).let {
            networkEntityMapper.toDomainEntityList(it)
        }
        val lastPosition = peopleList.size
        val itemCount = newList.size
        peopleList.addAll(newList)
        _listOfPeopleLiveData.value = Triple(peopleList, lastPosition, itemCount)
    }

    /**
     * If [peopleList] is empty, then fill the data of the first page from api request.
     */
    suspend fun initPeopleSet(){
        if (peopleList.isEmpty()) {
            pageNumber = 1
            getNextSetOfPeople()
        }
    }

}