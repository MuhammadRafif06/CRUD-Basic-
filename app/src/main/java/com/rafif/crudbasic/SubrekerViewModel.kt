package com.rafif.crudbasic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafif.crudbasic.db.Subreker
import com.rafif.crudbasic.repository.SubrekerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubrekerViewModel(private val repository : SubrekerRepository):
    ViewModel() {

        val inputName = MutableLiveData<String>()
        val inputEmail = MutableLiveData<String>()

        val saveOrUpdateButtonText = MutableLiveData<String>()
        val clearAllOrDeleteButtonText = MutableLiveData<String>()

        val subreker = repository.subreker

        init {
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }

    fun saveOrUpdate(){
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subreker(0, name, email))
        inputName.value = ""
        inputEmail.value = ""
    }

    fun clearAllOrDelete(){
        clearAll()
    }
    fun insert(subreker: Subreker){
        viewModelScope.launch (Dispatchers.IO){
            repository.insert(subreker)
        }
    }
    fun delete(subreker: Subreker){
        viewModelScope.launch (Dispatchers.IO){
            repository.delete(subreker)
        }
    }
    fun update(subreker: Subreker){
        viewModelScope.launch (Dispatchers.IO){
            repository.update(subreker)
        }
    }
    fun clearAll(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAll()
        }
    }
}