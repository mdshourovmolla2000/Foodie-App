package com.shourov.foodie.views.authPage.forgotPasswordPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentNewPasswordBinding
import com.shourov.foodie.utils.showSuccessToast

class NewPasswordFragment : Fragment() {

    private lateinit var binding: FragmentNewPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewPasswordBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            nextButton.setOnClickListener {
                requireContext().showSuccessToast("Login now")
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}