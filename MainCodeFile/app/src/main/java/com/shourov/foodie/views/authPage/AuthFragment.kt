package com.shourov.foodie.views.authPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentAuthBinding
import com.shourov.foodie.utils.NavigationHelper

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        binding.apply {
            loginButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_authFragment_to_loginFragment) }

            signupButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_authFragment_to_signupFragment) }
        }

        return binding.root
    }
}