package me.mehdi.mymoney.db

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CostViewModel(private val repository: CostRepository): ViewModel() {

    val costs : LiveData<List<Cost>> = repository.costs.asLiveData()

    fun addCost(vararg cost: Cost) = viewModelScope.launch{
        repository.addCost(*cost)
    }

    fun findCost(id: Int): Flow<Cost> {
        return repository.findCost(id)
    }
}




class CostViewModelFactory(private val repository: CostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CostViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
