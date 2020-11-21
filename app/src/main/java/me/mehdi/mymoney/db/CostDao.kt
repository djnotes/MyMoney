package me.mehdi.mymoney.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CostDao {
    @Query("SELECT * FROM cost")
    fun getCosts(): Flow<List<Cost>>

    @Insert()
    suspend fun addCost(vararg cost: Cost)

    @Delete()
    fun deleteCost(vararg cost: Cost)


}