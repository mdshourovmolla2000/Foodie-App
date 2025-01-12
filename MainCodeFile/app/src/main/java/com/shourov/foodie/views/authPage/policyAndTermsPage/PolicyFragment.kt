package com.shourov.foodie.views.authPage.policyAndTermsPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.databinding.FragmentPolicyBinding

class PolicyFragment : Fragment() {

    private lateinit var binding: FragmentPolicyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPolicyBinding.inflate(inflater, container, false)

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }
        }

        return binding.root
    }
}