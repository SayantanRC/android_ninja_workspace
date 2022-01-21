package designstring.androidninja.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import designstring.androidninja.R
import designstring.androidninja.databinding.ActivityMainBinding
import designstring.androidninja.domain.models.DomainEntity
import designstring.androidninja.ui.adapters.RecyclerViewAdapter
import designstring.androidninja.utils.tryIt
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val peopleList = ArrayList<DomainEntity>(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RecyclerViewAdapter(peopleList)
        binding.peopleList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.listOfPeopleLiveData.observe(this){
            peopleList.addAll(it)
            adapter.notifyItemRangeChanged(peopleList.size - it.size, it.size)
        }

        tryIt({
            lifecycleScope.launch {
                viewModel.initPeopleSet()
            }
        }, this@MainActivity)

        binding.loadMoreButton.setOnClickListener {
            tryIt({
                lifecycleScope.launch {
                    viewModel.getNextSetOfPeople()
                }
            }, this)
        }
    }
}