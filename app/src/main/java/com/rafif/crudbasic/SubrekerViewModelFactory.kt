package com.rafif.crudbasic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rafif.crudbasic.repository.SubrekerRepository
import java.lang.IllegalArgumentException

class SubrekerViewModelFactory(private val repository: SubrekerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubrekerViewModel::class.java)){
            return SubrekerViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}