package me.mehdi.mymoney.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CostViewModel(private val repository: CostRepository): ViewModel() {

    val costs : LiveData<List<Cost>> = repository.costs as LiveData<List<Cost>>

    fun insert(vararg cost: Cost) = viewModelScope.launch{
        repository.addCost(*cost)
    }
}