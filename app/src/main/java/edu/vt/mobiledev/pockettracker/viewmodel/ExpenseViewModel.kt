package edu.vt.mobiledev.pockettracker.viewmodel

import android.app.Application
import androidx.lifecycle.*
import edu.vt.mobiledev.pockettracker.data.Expense
import edu.vt.mobiledev.pockettracker.data.PocketDatabase
import edu.vt.mobiledev.pockettracker.data.ExpenseRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = PocketDatabase.getDatabase(application).expenseDao()
    private val repository = ExpenseRepository(dao)

    val expenses: LiveData<List<Expense>> = repository.expenses.asLiveData()

    val totalExpenses: LiveData<Double> = repository.expenses.map { expensesList ->
        expensesList.sumOf { it.amount }
    }.asLiveData()

    val expensesByCategory: LiveData<Map<String, Double>> = repository.expenses.map { expensesList ->
        expensesList.groupBy { it.category }
            .mapValues { (_, list) -> list.sumOf { it.amount } }
    }.asLiveData()

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insert(expense)
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.delete(expense)
        }
    }

    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            repository.update(expense)
        }
    }
}