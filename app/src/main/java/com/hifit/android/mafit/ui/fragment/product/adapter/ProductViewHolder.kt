package com.hifit.android.mafit.ui.fragment.product.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.data.model.ProductItem
import com.hifit.android.mafit.databinding.ItemProductBinding

class ProductViewHolder(
    parent: ViewGroup, listener: ProductAdapterListener
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
) {
    private val binding = ItemProductBinding.bind(itemView)

    init {
        // set 리스너
        binding.root.setOnClickListener {
            listener.onItemClicked(adapterPosition)
        }
    }

    fun bind(item: ProductItem) {
        // set item
/*        Glide.with(itemView.context)
            .load(R.drawable.ic_sample1)
            .centerCrop()
            .into(binding.itemProductImg)*/

        binding.itemProductTxtBrandName.text = item.brand
        binding.itemProductTxtProductName.text = item.name
        binding.itemProductTxtCoin.text = "${item.coin}코인"
    }
}