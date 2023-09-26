package com.hifit.android.mafit.ui.fragment.product.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.data.model.ProductItem
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseAdapterListener
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseViewHolder


class ProductAdapter(private val listener: ProductAdapterListener) :
    ListAdapter<ProductItem, ProductViewHolder>(object : DiffUtil.ItemCallback<ProductItem>() {
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem == newItem
        }
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}