package designstring.androidninja.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import designstring.androidninja.databinding.ActivityMainBinding
import designstring.androidninja.domain.models.DomainEntity
import designstring.androidninja.ui.adapters.RecyclerViewAdapter
import designstring.androidninja.utils.tryIt
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    /**
     * Store all people details.
     */
    private val allPeopleList = ArrayList<DomainEntity>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Set recycler view adapter
         */
        val adapter = RecyclerViewAdapter(allPeopleList)
        binding.peopleList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        /**
         * Observe change of data from view model.
         * If new data is received, update [allPeopleList],
         * notify the recyclerview adapter of the change.
         */
        viewModel.listOfPeopleLiveData.observe(this){
            val receivedList = it.first
            val position = it.second
            val itemCount = it.third
            allPeopleList.apply {
                clear()
                addAll(receivedList)
            }
            adapter.notifyItemRangeChanged(position, itemCount)
        }

        /**
         * On create initialise the people set.
         * On screen rotation this method will be called again,
         * but the function `initPeopleSet()` runs only if the data set in the
         * view model is empty.
         * If it is not empty, then the function will not run.
         */
        tryIt({
            lifecycleScope.launch {
                viewModel.initPeopleSet()
            }
        }, this@MainActivity)

        /**
         * On clicking `Load More` load the next page.
         */
        binding.loadMoreButton.setOnClickListener {
            tryIt({
                lifecycleScope.launch {
                    viewModel.getNextSetOfPeople()
                }
            }, this)
        }
    }
}