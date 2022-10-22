package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class Login : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )

        binding.login.setOnClickListener(View.OnClickListener {
            navigateToOnBoarding()
        })
        binding.register.setOnClickListener(View.OnClickListener {
            navigateToOnBoarding()
        })
        return binding.root
    }

    private fun navigateToOnBoarding() {
        var action = LoginDirections.actionLoginFragmentToWelcome()
        findNavController().navigate(action)
    }
}