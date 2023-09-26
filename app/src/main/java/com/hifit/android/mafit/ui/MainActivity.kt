package com.hifit.android.mafit.ui

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.hifit.android.mafit.R
import com.hifit.android.mafit.databinding.ActivityMainBinding
import com.hifit.android.mafit.util.setStatusBarColor
import com.hifit.android.mafit.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.startFragment, R.id.loginFragment, R.id.exerciseFragment, R.id.productFragment, R.id.orderFragment -> setStatusBarColor(getColor(R.color.violet))
                else -> setStatusBarColor(getColor(R.color.white))
            }
        }
    }
}



