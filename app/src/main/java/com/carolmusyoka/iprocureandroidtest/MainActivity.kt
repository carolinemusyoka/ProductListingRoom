package com.carolmusyoka.iprocureandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.carolmusyoka.iprocureandroidtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController

        binding.navView.setOnItemSelectedListener { menu ->
            when(menu.itemId){
               R.id.navigation_home -> {
                 navController.navigate(R.id.dashFragment)
                   return@setOnItemSelectedListener true
               }
                R.id.navigation_products_sort -> {
                    navController.navigate(R.id.inventoryFragment)
                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }
}