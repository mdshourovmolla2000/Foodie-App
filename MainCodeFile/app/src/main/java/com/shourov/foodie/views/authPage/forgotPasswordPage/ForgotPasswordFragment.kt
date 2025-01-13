package com.shourov.foodie.views.authPage.forgotPasswordPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentForgotPasswordBinding
import com.shourov.foodie.utils.NavigationHelper

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            nextButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_forgotPasswordFragment_to_verificationFragment) }
        }


        return binding.root
    }
}