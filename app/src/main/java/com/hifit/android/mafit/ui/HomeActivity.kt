package com.hifit.android.mafit.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hifit.android.mafit.R
import com.hifit.android.mafit.databinding.ActivityHomeBinding
import com.hifit.android.mafit.databinding.ActivityMainBinding
import com.hifit.android.mafit.util.setStatusBarColor
import com.hifit.android.mafit.viewmodel.MainViewModel
import java.util.ArrayList

class HomeActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }
    private val binding: ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNav
            .setupWithNavController(navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    setStatusBarColor(getColor(R.color.violet3))
                    binding.bottomNav.visibility = View.VISIBLE
                }

                R.id.exerciseFragment, R.id.productFragment, R.id.myPageFragment -> {
                    setStatusBarColor(getColor(R.color.white))
                    binding.bottomNav.visibility = View.VISIBLE
                }


                else -> {
                    setStatusBarColor(getColor(R.color.white))
                    binding.bottomNav.visibility = View.GONE
                }
            }
        }
    }

    fun selectedItemId(menuId: Int) {
        binding.bottomNav.selectedItemId = menuId
    }
}



