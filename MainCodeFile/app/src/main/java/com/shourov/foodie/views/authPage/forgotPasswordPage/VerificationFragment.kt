package com.shourov.foodie.views.authPage.forgotPasswordPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentVerificationBinding
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showSuccessToast

class VerificationFragment : Fragment() {

    private lateinit var binding: FragmentVerificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerificationBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            nextButton.setOnClickListener {
                requireContext().showSuccessToast(pinView.text.toString())
                NavigationHelper.navigateTo(findNavController(), R.id.action_verificationFragment_to_newPasswordFragment)
            }
        }

        return binding.root
    }
}