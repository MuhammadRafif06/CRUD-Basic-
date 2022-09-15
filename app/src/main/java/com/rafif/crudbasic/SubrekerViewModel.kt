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

        val inputName = MutableLiveData<String?>()
        val inputEmail = MutableLiveData<String?>()

        val saveOrUpdateButtonText = MutableLiveData<String>()
        val clearAllOrDeleteButtonText = MutableLiveData<String>()

        val subreker = repository.subreker

        private lateinit var subrekerToUpdateOrDelete : Subreker

        private var isUpdateOrDelete = false

        init {
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }

    fun initUpdateAndDelete(subreker: Subreker){
        inputName.value = subreker.name
        inputEmail.value = subreker.email
        isUpdateOrDelete = true
        subrekerToUpdateOrDelete = subreker
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Clear"
    }

    fun saveOrUpdate(){
        if (isUpdateOrDelete){
            subrekerToUpdateOrDelete.name = inputName.value!!
            subrekerToUpdateOrDelete.email = inputEmail.value!!
            update(subrekerToUpdateOrDelete)
        }else{
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subreker(0, name, email))
            inputName.value = ""
            inputEmail.value = ""
        }
    }

    fun clearAllOrDelete(){
        if (isUpdateOrDelete){
            delete(subrekerToUpdateOrDelete)
        }else{
            clearAll()
        }
    }
    fun insert(subreker: Subreker){
        viewModelScope.launch {
            repository.insert(subreker)
        }
    }
    fun delete(subreker: Subreker){
        viewModelScope.launch {
            repository.delete(subreker)

            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }
    fun update(subreker: Subreker){
        viewModelScope.launch {
            repository.update(subreker)

            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "save"
            clearAllOrDeleteButtonText.value = "Clear All"
        }
    }
    fun clearAll(){
        viewModelScope.launch {
            repository.deleteAll()
        }
    }
}