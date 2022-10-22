package com.udacity.shoestore.screens.shoes

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.models.Shoe


class ShoesList : Fragment() {
    private lateinit var binding: FragmentShoesListBinding

    private val viewModel: ShoeViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {

        binding.addShoe.setOnClickListener {
            val action = ShoesListDirections.actionShoesListToShoeDetail()
            findNavController().navigate(action)
        }

        viewModel.shoesList.observe(viewLifecycleOwner) { response ->
            response?.let {
                if (it.isNotEmpty()) {
                    binding.listOfItems.removeAllViews()
                    for (shoe in it) {
                        addShoes(shoe)
                    }
                }
            }
        }
    }

    private fun addShoes(shoe: Shoe) {
        var shoeItem: View = layoutInflater.inflate(R.layout.fragment_shoe_item, null)
        var name: TextView = shoeItem.findViewById(R.id.name)
        var company: TextView = shoeItem.findViewById(R.id.company)
        var description: TextView = shoeItem.findViewById(R.id.description)
        var size: TextView = shoeItem.findViewById(R.id.size)
        var image: ImageView = shoeItem.findViewById(R.id.shoe_image)
        name.text = "Name: ${shoe.name}"
        description.text = "Description: ${shoe.description}"
        company.text = "Company: ${shoe.company}"
        size.text = "Size: ${shoe.size.toInt()}"
        if (shoe.images.isNotEmpty()) image.setImageResource(shoe.images.first())
        binding.listOfItems.addView(shoeItem)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoutItem -> findNavController().navigate(ShoesListDirections.actionShoesListToLoginFragment())
        }
        return super.onOptionsItemSelected(item)

    }


}