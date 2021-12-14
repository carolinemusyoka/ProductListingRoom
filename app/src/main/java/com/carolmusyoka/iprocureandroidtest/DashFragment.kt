package com.carolmusyoka.iprocureandroidtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.carolmusyoka.iprocureandroidtest.databinding.FragmentDashBinding


class DashFragment : Fragment() {

    private lateinit var _binding: FragmentDashBinding
    private val binding get() = _binding


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
        binding.btnAddProduct.setOnClickListener {
            findNavController().navigate(R.id.action_dashFragment_to_addProductFragment)
        }
    }

}