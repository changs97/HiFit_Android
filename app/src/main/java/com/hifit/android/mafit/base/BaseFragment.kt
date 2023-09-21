package com.hifit.android.mafit.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VDB : ViewDataBinding>(
    layoutResId: Int
) : Fragment(layoutResId) {
    private val toastList = arrayListOf<Toast>()

    private var _binding: VDB? = null
    protected val binding: VDB get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DataBindingUtil.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onStop() {
        for (toast in toastList) {
            toast.cancel()
        }
        toastList.clear()
        super.onStop()
    }

    fun showCustomToast(message: String) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.show()
        toastList.add(toast)
    }

}