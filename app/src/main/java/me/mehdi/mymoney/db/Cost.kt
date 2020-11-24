package me.mehdi.mymoney.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cost(
    @ColumnInfo(name = "cost_id") @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "cost_name") val costName: String,
    @ColumnInfo(name = "cost_value") val costValue: Double
)