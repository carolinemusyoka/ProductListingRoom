package com.carolmusyoka.iprocureandroidtest.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carolmusyoka.iprocureandroidtest.R
import com.carolmusyoka.iprocureandroidtest.data.db.ProductsDatabase
import com.carolmusyoka.iprocureandroidtest.data.repository.ProductsRepository
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModel
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModelFactory
import com.carolmusyoka.iprocureandroidtest.databinding.FragmentDashBinding


class DashFragment : Fragment() {

    private lateinit var _binding: FragmentDashBinding
    private val binding get() = _binding
    private lateinit var productsAdapter: AllProductsAdapter
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.let {
            val productsRepository = ProductsRepository(ProductsDatabase(requireContext()))
            val factory = ProductViewModelFactory(productsRepository)
            productViewModel = ViewModelProvider(requireActivity(), factory)
                .get(ProductViewModel::class.java)
        }
        binding.editSearch.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchProduct(binding.editSearch.text.toString())
                Log.d("TAG", "onTextChanged: ${binding.editSearch.text.toString()}")
            }

            override fun afterTextChanged(p0: Editable?) {


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


        activity.let {
            val productsRepository = ProductsRepository(ProductsDatabase(requireContext()))
            val factory = ProductViewModelFactory(productsRepository)
            productViewModel = ViewModelProvider(requireActivity(), factory)
                .get(ProductViewModel::class.java)
        }
        binding.btnAddProduct.setOnClickListener {
            findNavController().navigate(R.id.action_dashFragment_to_addProductFragment)
        }
        productsAdapter = AllProductsAdapter(listOf())
        binding.products.layoutManager = LinearLayoutManager(context)
        binding.products.adapter = productsAdapter

        productViewModel.allProductItems().observe(viewLifecycleOwner, {
            productsAdapter.productItemList = it
            productsAdapter.notifyDataSetChanged()

            if (it.isNullOrEmpty()){

            }

        })
    }

    private fun searchProduct(title: String) {
        var searchText = title
        searchText = title

        Log.d("TAG", "searchProduct: $searchText")
        productViewModel.searchDatabase(searchText).observe(viewLifecycleOwner , {list ->

            list.let {
                productsAdapter.productItemList = it
            }
        })

    }

}