package com.rafif.crudbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafif.crudbasic.adapter.MyRecyclerViewAdapter
import com.rafif.crudbasic.databinding.ActivityMainBinding
import com.rafif.crudbasic.db.Subreker
import com.rafif.crudbasic.db.SubrekerDatabase
import com.rafif.crudbasic.repository.SubrekerRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subrekerViewModel: SubrekerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubrekerDatabase.getInstance(application).subrekerDAO
        val repository = SubrekerRepository(dao)
        val factory = SubrekerViewModelFactory(repository)

        subrekerViewModel = ViewModelProvider(this, factory)[SubrekerViewModel::class.java]

        binding.myViewModel = subrekerViewModel
        binding.lifecycleOwner = this

//        displaySubrekerList()
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaySubrekerList()
    }

    private fun displaySubrekerList(){
        subrekerViewModel.subreker.observe(this, Observer {
            Log.i("tag", "displaySubrekerList: $it")
            binding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it, { selectedItem: Subreker -> listItemClicked(selectedItem) })
        })
    }
    private fun listItemClicked(subreker: Subreker){
        subrekerViewModel.initUpdateAndDelete(subreker)
    }
}