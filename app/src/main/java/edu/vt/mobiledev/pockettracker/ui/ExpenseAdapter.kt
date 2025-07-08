package edu.vt.mobiledev.pockettracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.vt.mobiledev.pockettracker.data.Expense
import edu.vt.mobiledev.pockettracker.databinding.ItemExpenseBinding

class ExpenseAdapter : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private var expenses: List<Expense> = emptyList()

    interface OnItemInteractionListener {
        fun onExpenseLongClick(expense: Expense)
    }

    private var listener: OnItemInteractionListener? = null

    fun setOnItemInteractionListener(listener: OnItemInteractionListener) {
        this.listener = listener
    }

    fun submitList(list: List<Expense>) {
        expenses = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ExpenseViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.bind(expenses[position])
    }

    override fun getItemCount(): Int = expenses.size

    class ExpenseViewHolder(
        private val binding: ItemExpenseBinding,
        private val listener: OnItemInteractionListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val clickedExpense = binding.root.tag as? Expense
                    clickedExpense?.let {
                        listener?.onExpenseLongClick(it)
                    }
                }
                true
            }
        }

        fun bind(expense: Expense) {
            binding.amountText.text = "$${expense.amount}"
            binding.categoryText.text = expense.category
            binding.descriptionText.text = expense.description
            binding.root.tag = expense
        }
    }
}