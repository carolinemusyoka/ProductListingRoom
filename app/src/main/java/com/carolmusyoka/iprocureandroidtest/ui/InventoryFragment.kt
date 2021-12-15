package com.carolmusyoka.iprocureandroidtest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carolmusyoka.iprocureandroidtest.R
import com.carolmusyoka.iprocureandroidtest.data.db.ProductsDatabase
import com.carolmusyoka.iprocureandroidtest.data.model.Products
import com.carolmusyoka.iprocureandroidtest.data.repository.ProductsRepository
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModel
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModelFactory
import com.carolmusyoka.iprocureandroidtest.databinding.FragmentInventoryBinding
import com.google.android.material.tabs.TabLayoutMediator

val tabsArray = arrayOf("All", "Minerals", "Equipment", "Cereals")
class InventoryFragment : Fragment() {
    private lateinit var _binding: FragmentInventoryBinding
    private val binding get() = _binding
    private var listOfItems: List<Products> ? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabsArray[position]
        }.attach()
    }
}