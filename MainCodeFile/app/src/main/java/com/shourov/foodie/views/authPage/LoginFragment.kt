package com.shourov.foodie.views.authPage

import android.app.ActivityOptions
import android.content.Intent
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
import com.shourov.foodie.databinding.FragmentLoginBinding
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showSuccessToast
import com.shourov.foodie.views.MainActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signupText()

        binding.apply {
            backButton.setOnClickListener { findNavController().popBackStack() }

            forgotPasswordButton.setOnClickListener { NavigationHelper.navigateTo(findNavController(), R.id.action_loginFragment_to_forgotPasswordFragment) }

            loginButton.setOnClickListener {
                requireContext().showSuccessToast("Successfully logged in")
                val intent = Intent(requireActivity(), MainActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(requireContext(), R.anim.enter, R.anim.exit)
                startActivity(intent, options.toBundle())
                requireActivity().finish()
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


