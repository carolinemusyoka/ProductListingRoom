package com.carolmusyoka.iprocureandroidtest.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.carolmusyoka.iprocureandroidtest.R
import com.carolmusyoka.iprocureandroidtest.data.db.ProductsDatabase
import com.carolmusyoka.iprocureandroidtest.data.model.Products
import com.carolmusyoka.iprocureandroidtest.data.repository.ProductsRepository
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModel
import com.carolmusyoka.iprocureandroidtest.data.viewmodel.ProductViewModelFactory

import com.carolmusyoka.iprocureandroidtest.databinding.FragmentAddProductBinding
import dev.ronnie.github.imagepicker.ImagePicker
import dev.ronnie.github.imagepicker.ImageResult


class AddProductFragment : Fragment() {
    private lateinit var _binding: FragmentAddProductBinding
    private val binding get() = _binding
    private val maxHeightWidth = 1080
    private var bitmap: Bitmap? = null
    private lateinit var imagePicker: ImagePicker
    private val _bitmapLivedata = MutableLiveData<Bitmap>()
    private val bitmapLiveData: LiveData<Bitmap> get() = _bitmapLivedata
    lateinit var productViewModel: ProductViewModel
    private var itemSelected : String? = null
    private var image: String? = null
    private var uriMain: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagePicker = ImagePicker(this)

        val categories = resources.getStringArray(R.array.categories)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)
        (binding.category.editText as? AutoCompleteTextView)?.setAdapter(arrayAdapter)

        binding.autoTv.onItemClickListener =
            AdapterView.OnItemClickListener{ parent, arg1 , pos, id ->
                itemSelected = arrayAdapter.getItem(pos)
                Log.d("TAG", "onViewCreated: $itemSelected")
            }


        activity.let {
            val productRepository =ProductsRepository(ProductsDatabase(requireContext()))
            val factory = ProductViewModelFactory(productRepository)
            productViewModel = ViewModelProvider(requireActivity(), factory)
                .get(ProductViewModel::class.java)
        }

        binding.save.setOnClickListener {
             saveProduct()
        }

        bitmapLiveData.observe(viewLifecycleOwner, {bitmap ->
             this.bitmap = bitmap
            image = this.bitmap.toString()
            binding.imageAdded.setImageBitmap(this.bitmap)
            Log.d("TAG", "onViewCreated ${this.bitmap}: ")
        })

        binding.openCamera.setOnClickListener { takeFromCamera() }
        binding.openGallery.setOnClickListener { pickFromGallery() }
    }

    private fun saveProduct() {


        val productName = binding.productName.editText?.text.toString()
        val productCode = binding.productCode.editText?.text.toString()
        val manufacturer = binding.manufacturer.editText?.text.toString()
        val distributer = binding.distributer.editText?.text.toString()
        val unitPrice = binding.unitPrice.editText?.text.toString()
        val retailPrice = binding.retailPrice.editText?.text.toString()
        val agentPrice = binding.agentPrice.editText?.text.toString()
        val wholeSalePrice = binding.wholesalePrice.editText?.text.toString()
        //image


        Log.d("TAG", "saveProduct: $image")
        val product = Products(
            productId = 0,
            productName = productName,
            category = itemSelected!!,
            productType = "",
            manufacturer = manufacturer,
            distributer = distributer,
            vat = true,
            unitCost = unitPrice,
            retailPrice = retailPrice,
            agentPrice = agentPrice,
            wholesalePrice = wholeSalePrice,
            imageAdded = uriMain.toString()
        )
        productViewModel.insert(product)
        findNavController().navigate(R.id.action_addProductFragment_to_dashFragment)
    }

    private fun pickFromGallery() {
        imagePicker.pickFromStorage { imageResult ->
            when (imageResult) {
                is ImageResult.Success -> {
                    uriMain = imageResult.value
                    getLargeBitmap(uriMain!!)
                }
                is ImageResult.Failure -> {
                    val errorString = imageResult.errorString
                    Toast.makeText(context, errorString, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun takeFromCamera() {
        imagePicker.takeFromCamera { imageResult ->
            when(imageResult){
                is ImageResult.Success ->{
                    uriMain = imageResult.value
                    getLargeBitmap(uriMain!!)
                }
                is ImageResult.Failure ->{
                    val errorString = imageResult.errorString
                    Toast.makeText(context, errorString, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun getLargeBitmap(uri: Uri) {
        Glide.with(this)
            .asBitmap()
            .override(maxHeightWidth, maxHeightWidth)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    _bitmapLivedata.postValue(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }


}