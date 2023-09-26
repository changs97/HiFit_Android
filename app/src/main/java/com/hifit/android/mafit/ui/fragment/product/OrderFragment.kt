package com.hifit.android.mafit.ui.fragment.product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ExerciseItem
import com.hifit.android.mafit.data.model.ProductItem
import com.hifit.android.mafit.databinding.FragmentExerciseBinding
import com.hifit.android.mafit.databinding.FragmentOrderBinding
import com.hifit.android.mafit.databinding.FragmentProductBinding
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExerciseAdapterListener
import com.hifit.android.mafit.ui.fragment.exercise.adapter.ExercisePageAdapter
import com.hifit.android.mafit.ui.fragment.product.adapter.ProductAdapter
import com.hifit.android.mafit.ui.fragment.product.adapter.ProductAdapterListener
import com.hifit.android.mafit.util.toKRW
import com.hifit.android.mafit.viewmodel.MainViewModel
import java.text.DecimalFormat

class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {
    private val viewModel: MainViewModel by activityViewModels()
    private val args: OrderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        val productItem = args.productItem

        Glide.with(this).load(R.drawable.exercise_sample).centerCrop().into(binding.orderImg)

        binding.orderTxtBrand.text = productItem.brand
        binding.orderTxtProductName.text = productItem.name

        val samplePrice = 12000

        binding.orderTxtPrice.text = samplePrice.toKRW()
        binding.orderTxtAmount.text = samplePrice.toKRW()

        var curQuantity = 1
        var maxQuantity = 10
        var minQuantity = 1

        if (curQuantity == 1 && maxQuantity == 1) {
            binding.orderImgIncrease.isEnabled = false
            binding.orderImgDecrease.isEnabled = false
            binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
            binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
        } else if (curQuantity == 0 && maxQuantity == 1) {
            // maxQuantity 가 1 이고 0개 담겨있는 경우
            binding.orderImgIncrease.isEnabled = false
            binding.orderImgDecrease.isEnabled = false
            binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
            binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
        } else if (minQuantity == maxQuantity) {
            // 최소 구매 수량과 최대 구매 수량이 같은 경우
            binding.orderImgIncrease.isEnabled = false
            binding.orderImgDecrease.isEnabled = false
            binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
            binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
        } else {
            binding.orderImgIncrease.setOnClickListener {
                val amountOfItem = binding.orderTxtProductCount.text.toString().toInt()
                if (amountOfItem + 1 < maxQuantity) {
                    binding.orderImgIncrease.isEnabled = true
                    binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_on)
                } else {
                    binding.orderImgIncrease.isEnabled = false
                    binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
                }

                binding.orderTxtProductCount.text = "${amountOfItem.inc()}"
                binding.orderTxtAmount.text =
                    "${DecimalFormat("#,###").format(samplePrice * amountOfItem.inc())}원"
                binding.orderImgDecrease.isEnabled = true
                binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_on)
            }

            binding.orderImgDecrease.setOnClickListener {
                val amountOfItem = binding.orderTxtProductCount.text.toString().toInt()
                if (amountOfItem - 1 > minQuantity) {
                    binding.orderImgDecrease.isEnabled = true
                    binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_on)
                } else {
                    binding.orderImgDecrease.isEnabled = false
                    binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
                }

                binding.orderTxtProductCount.text = "${amountOfItem.dec()}"
                binding.orderTxtAmount.text =
                    "${DecimalFormat("#,###").format(samplePrice * amountOfItem.dec())}원"
                binding.orderImgIncrease.isEnabled = true
                binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_on)
            }

            if (minQuantity >= curQuantity) {
                binding.orderImgDecrease.isEnabled = false
                binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
            } else if (maxQuantity <= curQuantity) {
                binding.orderImgIncrease.isEnabled = false
                binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
            } else {
                binding.orderImgDecrease.isEnabled = true
                binding.orderImgIncrease.isEnabled = true
                binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_on)
                binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_on)
            }

        }
    }
}