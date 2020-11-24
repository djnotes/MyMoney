package me.mehdi.mymoney.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Cost::class], version = 1)
abstract class AppDb: RoomDatabase() {
    abstract fun costDao(): CostDao

    companion object {
        var INSTANCE: AppDb? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDb {
            return INSTANCE ?: synchronized(this){
             val instance = Room.databaseBuilder(context, AppDb::class.java ,"costdb.db")
                .build()
                INSTANCE = instance
                instance
            }

        }
    }
}