package edu.vt.mobiledev.pockettracker.data

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {
    val expenses: Flow<List<Expense>> = dao.getAllExpenses()

    suspend fun insert(expense: Expense) = dao.insert(expense)
    suspend fun delete(expense: Expense) = dao.delete(expense)
    suspend fun update(expense: Expense) = dao.update(expense)
}
