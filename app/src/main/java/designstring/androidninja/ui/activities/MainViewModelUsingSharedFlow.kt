package designstring.androidninja.ui.activities

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import designstring.androidninja.data.Repository
import designstring.androidninja.domain.mappers.NetworkEntityMapper
import designstring.androidninja.domain.models.DomainEntity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModelUsingSharedFlow
@Inject
constructor(
    private val networkEntityMapper: NetworkEntityMapper,
    private val repository: Repository,
): ViewModel() {

    private var pageNumber = 1

    /**
     * Flow which main activity observes for new data.
     * A cache of 100 is kept.
     */
    private val _peopleFlow = MutableSharedFlow<DomainEntity>(100)
    val peopleFlow = _peopleFlow.asSharedFlow()

    /**
     * Function to call [repository] method to load the next set of data.
     * Also uses the [networkEntityMapper] to convert the received data to domain model.
     */
    suspend fun getNextSetOfPeople() {
        repository.getPeopleDataFromNetwork(pageNumber++).forEach {
            _peopleFlow.emit(networkEntityMapper.toDomainEntity(it))
        }
    }

    /**
     * If cache of [_peopleFlow] is empty, then fill the data of the first page from api request.
     */
    suspend fun initPeopleSet(){
        if (_peopleFlow.replayCache.isEmpty()) {
            pageNumber = 1
            getNextSetOfPeople()
        }
    }

}