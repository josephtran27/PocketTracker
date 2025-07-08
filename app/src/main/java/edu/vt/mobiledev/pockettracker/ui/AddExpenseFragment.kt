package edu.vt.mobiledev.pockettracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.vt.mobiledev.pockettracker.data.Expense
import edu.vt.mobiledev.pockettracker.databinding.FragmentAddExpenseBinding
import edu.vt.mobiledev.pockettracker.viewmodel.ExpenseViewModel
import kotlinx.coroutines.launch
import java.util.*

class AddExpenseFragment : Fragment() {

    private var _binding: FragmentAddExpenseBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExpenseViewModel
    private val args: AddExpenseFragmentArgs by navArgs()
    private var currentExpense: Expense? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ExpenseViewModel::class.java]

        val expenseIdString = args.expenseId

        if (expenseIdString != null) {
            val expenseId = UUID.fromString(expenseIdString)

            lifecycleScope.launch {

                viewModel.expenses.value?.find { it.id == expenseId }?.let { expense ->
                    currentExpense = expense
                    binding.amountInput.setText(expense.amount.toString())
                    binding.categoryInput.setText(expense.category)
                    binding.descriptionInput.setText(expense.description)
                    binding.saveButton.text = "Update"

                }
            }
        }


        binding.saveButton.setOnClickListener {
            val amountStr = binding.amountInput.text.toString()
            val category = binding.categoryInput.text.toString()
            val description = binding.descriptionInput.text.toString()

            if (amountStr.isBlank() || category.isBlank()) {
                Toast.makeText(requireContext(), "Amount and Category cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (currentExpense == null) {
                val newExpense = Expense(
                    amount = amount,
                    category = category,
                    date = Calendar.getInstance().time,
                    description = description.ifBlank { null }
                )
                viewModel.addExpense(newExpense)
                Toast.makeText(requireContext(), "Expense Added!", Toast.LENGTH_SHORT).show()
            } else {
                val updatedExpense = currentExpense!!.copy(
                    amount = amount,
                    category = category,
                    description = description.ifBlank { null }


                )
                viewModel.updateExpense(updatedExpense)
                Toast.makeText(requireContext(), "Expense Updated!", Toast.LENGTH_SHORT).show()
            }

            binding.amountInput.text.clear()
            binding.categoryInput.text.clear()
            binding.descriptionInput.text.clear()
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}