package edu.vt.mobiledev.pockettracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val amount: Double,
    val category: String,
    val date: Date,
    val description: String? = null,
    val photoPath: String? = null
)
