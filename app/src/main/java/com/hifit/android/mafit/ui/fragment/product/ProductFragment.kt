package com.hifit.android.mafit.ui.fragment.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hifit.android.mafit.R
import com.hifit.android.mafit.base.BaseFragment
import com.hifit.android.mafit.data.model.ProductItem
import com.hifit.android.mafit.databinding.FragmentProductBinding
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
            ProductItem("https://shop-phinf.pstatic.net/20220624_290/1655998258517QThc5_PNG/57134100934040595_1514577604.png?type=m510", "모두의 단백질", "모두의 단백질 고단백 돼지육포 포돈 40g 돼지고기 육포 단백질 식품", 80),
            ProductItem("https://shop-phinf.pstatic.net/20220624_185/1655997805128yKUdl_PNG/57133647615639690_966436732.png?type=m510", "모두의 단백질", "모두의 단백질 고단백 돼지육포 포돈 40g x 12팩", 80),
            ProductItem("https://shop-phinf.pstatic.net/20220624_290/1655998258517QThc5_PNG/57134100934040595_1514577604.png?type=m510", "모두의 단백질", "모두의 단백질 고단백 돼지육포 포돈 40g x 24팩", 80),
            ProductItem("https://shop-phinf.pstatic.net/20220324_36/1648084949876ciyHf_PNG/49220729502980496_736494807.png?type=m510", "모두의 단백질", "모두의 단백질 수비돈 풀드포크 바베큐맛 칠리맛 두 가지 맛 패키지 총 4팩 중량 480g", 80)
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