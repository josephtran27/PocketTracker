package edu.vt.mobiledev.pockettracker.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.vt.mobiledev.pockettracker.databinding.FragmentExpenseListBinding
import edu.vt.mobiledev.pockettracker.viewmodel.ExpenseViewModel
import edu.vt.mobiledev.pockettracker.data.Expense

class ExpenseListFragment : Fragment(), ExpenseAdapter.OnItemInteractionListener {
    private var _binding: FragmentExpenseListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var adapter: ExpenseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(requireActivity())[ExpenseViewModel::class.java]

        adapter = ExpenseAdapter()
        adapter.setOnItemInteractionListener(this)
        binding.expenseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.expenseRecyclerView.adapter = adapter

        viewModel.expenses.observe(viewLifecycleOwner) { expenseList ->
            adapter.submitList(expenseList)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onExpenseLongClick(expense: Expense) {
        AlertDialog.Builder(requireContext())
            .setTitle("Manage Expense")
            .setMessage("What would you like to do with this expense?")
            .setPositiveButton("Edit") { dialog, which ->
                val action = ExpenseListFragmentDirections.actionExpensesToAddEditExpense(expense.id.toString(), "Edit Expense")
                findNavController().navigate(action)
            }
            .setNegativeButton("Delete") { dialog, which ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Confirm Deletion")
                    .setMessage("Are you sure you want to delete this expense?")
                    .setPositiveButton("Yes") { _, _ ->
                        viewModel.deleteExpense(expense)
                    }
                    .setNegativeButton("No", null)
                    .show()
            }
            .setNeutralButton("Cancel", null)
            .show()
    }
}