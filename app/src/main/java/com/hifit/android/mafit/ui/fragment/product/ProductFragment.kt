package com.hifit.android.mafit.ui.fragment.product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.data.model.ProductItem
import com.hifit.android.mafit.databinding.FragmentExerciseBinding
import com.hifit.android.mafit.databinding.FragmentProductBinding
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseAdapterListener
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExercisePageAdapter
import com.hifit.android.mafit.ui.fragment.product.adapter.ProductAdapter
import com.hifit.android.mafit.ui.fragment.product.adapter.ProductAdapterListener
import com.hifit.android.mafit.viewmodel.MainViewModel

class ProductFragment : BaseFragment<FragmentProductBinding>(R.layout.fragment_product),
    ProductAdapterListener {
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter by lazy { ProductAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        val sampleData = arrayListOf(
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80),
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80),
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80),
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80),
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80),
            ProductItem("", "모두의 단백질", "단백질 52g 폴드포크 수비돈", 80)
        )

        binding.productRecycle.adapter = adapter
        adapter.submitList(sampleData)

    }

    override fun onItemClicked(position: Int) {
        val item = adapter.currentList[position]
        val action = ProductFragmentDirections.actionProductFragmentToOrderFragment(item)
        findNavController().navigate(action)
    }
}