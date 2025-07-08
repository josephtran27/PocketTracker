package edu.vt.mobiledev.pockettracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.vt.mobiledev.pockettracker.databinding.FragmentAnalyticsBinding
import edu.vt.mobiledev.pockettracker.viewmodel.ExpenseViewModel
import java.text.DecimalFormat

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ExpenseViewModel::class.java]

        val decimalFormat = DecimalFormat("#.00")

        viewModel.totalExpenses.observe(viewLifecycleOwner) { total ->
            binding.totalExpensesTextView.text = "Total: $${decimalFormat.format(total)}"
        }

        viewModel.expensesByCategory.observe(viewLifecycleOwner) { categoryMap ->
            val categorySummaryBuilder = StringBuilder("Spending by Category:\n")
            if (categoryMap.isEmpty()) {
                categorySummaryBuilder.append("No data available.")
            } else {
                categoryMap.entries.sortedBy { it.key }.forEach { (category, total) ->
                    categorySummaryBuilder.append("• $category: $${decimalFormat.format(total)}\n")
                }
            }
            binding.categorySummaryTextView.text = categorySummaryBuilder.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}