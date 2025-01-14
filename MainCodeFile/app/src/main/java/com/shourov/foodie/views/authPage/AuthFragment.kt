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

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            loginButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_authFragment_to_loginFragment) }

            signupButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_authFragment_to_signupFragment) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}