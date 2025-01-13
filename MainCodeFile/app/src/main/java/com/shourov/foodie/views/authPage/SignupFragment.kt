package com.shourov.foodie.views.authPage

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shourov.foodie.R
import com.shourov.foodie.databinding.FragmentSignupBinding
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showSuccessToast
import com.shourov.foodie.utils.showWarningToast

class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    private var isTermsChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        loginText()
        termsText()

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            termsCheckbox.setOnClickListener {
                isTermsChecked = !isTermsChecked
                val imageRes = if (isTermsChecked) R.drawable.checkbox_checked else R.drawable.checkbox_unchecked
                termsCheckbox.setImageResource(imageRes)
            }

            signupButton.setOnClickListener {
                if (isTermsChecked) {
                    requireContext().showSuccessToast("Account created successfully")
                    NavigationHelper.navigateTo(findNavController(), R.id.action_signupFragment_to_loginFragment)
                } else {
                    requireContext().showWarningToast("Please accept the terms & conditions")
                }
            }
        }

        return binding.root
    }

    private fun loginText() {
        val fullText = "Already have an account? Login"
        val spannableString = SpannableString(fullText)

        // Define the "Sign up" portion of the text
        val startIndex = fullText.indexOf("Login")
        val endIndex = startIndex + "Login".length

        // Use custom red color for "Sign up"
        val themeColor = ContextCompat.getColor(requireContext(), R.color.themeColor)
        spannableString.setSpan(ForegroundColorSpan(themeColor), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Make "Sign up" clickable without underline
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationHelper.navigateTo(findNavController(), R.id.action_signupFragment_to_loginFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = themeColor
            }
        }

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.loginTextview.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance() // Ensure the link is clickable
            highlightColor = Color.TRANSPARENT
        }
    }

    private fun termsText() {
        val fullText = "I agree to your privacy policy and terms & conditions"
        val spannableString = SpannableString(fullText)

        // Define the "privacy policy" portion of the text
        val privacyPolicyStartIndex = fullText.indexOf("privacy policy")
        val privacyPolicyEndIndex = privacyPolicyStartIndex + "privacy policy".length

        // Define the "terms & conditions" portion of the text
        val termsConditionsStartIndex = fullText.indexOf("terms & conditions")
        val termsConditionsEndIndex = termsConditionsStartIndex + "terms & conditions".length

        // Use custom red color for both "privacy policy" and "terms & conditions"
        val themeColor = ContextCompat.getColor(requireContext(), R.color.themeColor)
        spannableString.setSpan(ForegroundColorSpan(themeColor), privacyPolicyStartIndex, privacyPolicyEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ForegroundColorSpan(themeColor), termsConditionsStartIndex, termsConditionsEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Make both "privacy policy" and "terms & conditions" clickable without underline
        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationHelper.navigateTo(findNavController(), R.id.action_signupFragment_to_policyFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = themeColor
            }
        }

        val termsConditionsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                NavigationHelper.navigateTo(findNavController(), R.id.action_signupFragment_to_termsFragment)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = themeColor
            }
        }

        // Set the clickable spans for both "privacy policy" and "terms & conditions"
        spannableString.setSpan(privacyPolicyClickableSpan, privacyPolicyStartIndex, privacyPolicyEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(termsConditionsClickableSpan, termsConditionsStartIndex, termsConditionsEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Apply the spannable string to the TextView
        binding.termsTextview.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance() // Ensure the link is clickable
            highlightColor = Color.TRANSPARENT
        }
    }
}