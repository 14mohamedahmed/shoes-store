package com.udacity.shoestore.screens.shoes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {
    private var _shoesList = MutableLiveData<MutableList<Shoe>?>()
    val shoesList: LiveData<MutableList<Shoe>?>
        get() = _shoesList

    private val listOfShoes = mutableListOf<Shoe>() // 2, 3

    init {

        listOfShoes.add(Shoe("Sport Shoe", 43.0, "Nike", "Good Shoe", listOf(R.drawable.shoe1)))
        listOfShoes.add(Shoe("Sport Shoe", 80.0, "Zara", "Nice Shoe", listOf(R.drawable.shoe2)))
        _shoesList.value = listOfShoes
        Log.e("TAG", "init : " + listOfShoes.size.toString())
    }

    fun addShoeToList(name: String, description: String, company: String, size: String): Boolean {
        if (checkInputsValid(name, description, company, size)) {
            listOfShoes.add(
                (Shoe(name, size.trim().toDouble(), company, description))
            )
            _shoesList.value = listOfShoes
            return true
        }
        return false
    }

    private fun checkInputsValid(
        name: String,
        description: String,
        company: String,
        size: String
    ): Boolean {
        return !name.isNullOrBlank() || !description.isNullOrBlank() || !company.isNullOrBlank() || !size.isNullOrBlank()
    }
}