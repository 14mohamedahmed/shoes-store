package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val getAppBarConfig = getAppBarConfiguration()
        binding.toolbar.setupWithNavController(navController, getAppBarConfiguration())
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setNavigationOnClickListener {
            NavigationUI.navigateUp(navController, getAppBarConfig)
        }
    }

    private fun getAppBarConfiguration(): AppBarConfiguration {
        return AppBarConfiguration(
            setOf(
                R.id.login_fragment, R.id.welcome, R.id.shoesList
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return (findNavController(R.id.nav_host_fragment)).navigateUp() || super.onSupportNavigateUp()
    }
}