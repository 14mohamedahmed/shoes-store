package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.screens.shoes.ShoeViewModel


class ShoeDetail : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.cancel.setOnClickListener {
            goBack()
        }
        binding.submit.setOnClickListener {
            addShoe()
        }
    }

    private fun goBack() {
        var action = ShoeDetailDirections.actionShoeDetailToShoesList()
        findNavController().navigate(action)
    }

    private fun addShoe() {
        val isAdded: Boolean = viewModel.addShoeToList(
            binding.nameInput.text.toString(),
            binding.descInput.text.toString(),
            binding.companyInput.text.toString(),
            binding.sizeInput.text.toString()
        )
        if (isAdded) {
            goBack()

        } else {
            Toast.makeText(context, "Please add full information", Toast.LENGTH_LONG).show()
        }
    }
}