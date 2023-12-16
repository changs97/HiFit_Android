package com.hifit.android.mafit.ui.fragment.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.databinding.FragmentOrderBinding
import com.hifit.android.mafit.util.formatWithCommas
import com.hifit.android.mafit.viewmodel.MainViewModel
import java.text.DecimalFormat

@Suppress("KotlinConstantConditions")
class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {
    private val viewModel: MainViewModel by activityViewModels()
    private val args: OrderFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        val productItem = args.productItem

        Glide.with(this).load(productItem.img).centerCrop().into(binding.orderImg)

        binding.orderTxtProductName.text = productItem.name

        val coin = productItem.coin
        binding.orderTxtProductCoin.text = "${coin.formatWithCommas()}코인"
        binding.orderTxtAmount.text = "${coin.formatWithCommas()}코인"

        binding.orderImgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.orderBtn.setOnClickListener {
            showCustomToast("아직 구현되지 않은 기능입니다.")
        }

        // API 연동되면 상품마다 동적 수량으로 변경될 예정
        val curQuantity = 1
        val maxQuantity = 10
        val minQuantity = 1

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
                if (curQuantity + 1 < maxQuantity) {
                    binding.orderImgIncrease.isEnabled = true
                    binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_on)
                } else {
                    binding.orderImgIncrease.isEnabled = false
                    binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_off)
                }

                binding.orderTxtProductCount.text = "${curQuantity.inc()}"
                binding.orderTxtAmount.text =
                    "${DecimalFormat("#,###").format(coin * curQuantity.inc())}코인"
                binding.orderImgDecrease.isEnabled = true
                binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_on)
            }

            binding.orderImgDecrease.setOnClickListener {
                if (curQuantity - 1 > minQuantity) {
                    binding.orderImgDecrease.isEnabled = true
                    binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_on)
                } else {
                    binding.orderImgDecrease.isEnabled = false
                    binding.orderImgDecrease.setImageResource(R.drawable.ic_cart_item_minus_off)
                }

                binding.orderTxtProductCount.text = "${curQuantity.dec()}"
                binding.orderTxtAmount.text =
                    "${DecimalFormat("#,###").format(coin * curQuantity.dec())}코인"
                binding.orderImgIncrease.isEnabled = true
                binding.orderImgIncrease.setImageResource(R.drawable.ic_cart_item_plus_on)
            }
        }
    }
}