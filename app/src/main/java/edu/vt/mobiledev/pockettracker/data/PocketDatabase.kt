package edu.vt.mobiledev.pockettracker.data

import android.content.Context
import androidx.room.*
import java.util.concurrent.Executors

@Database(
    entities = [Expense::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PocketDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile private var INSTANCE: PocketDatabase? = null

        fun getDatabase(context: Context): PocketDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PocketDatabase::class.java,
                    "pocket_tracker_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
