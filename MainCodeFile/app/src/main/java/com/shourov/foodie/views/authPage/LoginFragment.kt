package com.shourov.foodie.views.authPage

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentLoginBinding
import com.shourov.foodie.utils.NavigationHelper

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        signupText()

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            forgotPasswordButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_loginFragment_to_forgotPasswordFragment) }
        }

        return binding.root
    }

    private fun signupText() {
        val fullText = "Don't have an account? Sign up"
        val spannableString = SpannableString(fullText)

        // Define the "Sign up" portion of the text
        val startIndex = fullText.indexOf("Sign up")
        val endIndex = startIndex + "Sign up".length

        // Use custom red color for "Sign up"
        val themeColor = ContextCompat.getColor(requireContext(), R.color.themeColor)
        spannableString.setSpan(ForegroundColorSpan(themeColor), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Make "Sign up" clickable without underline
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationHelper.navigateTo(findNavController(), R.id.action_loginFragment_to_signupFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = themeColor
            }
        }

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.signupTextview.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance() // Ensure the link is clickable
            highlightColor = Color.TRANSPARENT
        }

    }
}


