package com.hifit.android.mafit.ui.fragment.exercise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hifit.android.mafit.R
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.databinding.ItemExercisePageBinding

class ExercisePageViewHolder(
    parent: ViewGroup, private val listener: ExerciseAdapterListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_exercise_page, parent, false)
) {
    private val binding = ItemExercisePageBinding.bind(itemView)

    init {
        // set 리스너
    }

    fun bind(item: ArrayList<ExerciseItem>) {
        // set item
        val adapter = ExerciseAdapter(listener)
        binding.itemExercisePageRecycle.adapter = adapter
        adapter.submitList(item)

        binding.executePendingBindings()
    }
}