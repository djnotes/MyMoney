package me.mehdi.mymoney.db

import kotlinx.coroutines.flow.Flow

class CostRepository(private val costDao: CostDao){
    val costs: Flow<List<Cost>> = costDao.getCosts()


    suspend fun addCost(vararg cost: Cost){
        costDao.addCost(*cost)
    }

    suspend fun deleteCosts(vararg cost: Cost){
        costDao.deleteCost(*cost)
    }

    fun findCost(id: Int): Flow<Cost> {
        return costDao.findCost(id)
    }
}