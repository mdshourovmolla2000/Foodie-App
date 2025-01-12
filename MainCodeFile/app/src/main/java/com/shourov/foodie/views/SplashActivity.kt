package com.shourov.foodie.views

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shourov.foodie.R
import com.shourov.foodie.views.authPage.AuthActivity

class SplashActivity : AppCompatActivity() {

    private val waitingTime = 3 //time in second
    private val disableBackPressInSplashActivity = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, AuthActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(this@SplashActivity, R.anim.enter, R.anim.exit)
            startActivity(intent, options.toBundle())
            finish()
        }, waitingTime * 1000L)

        backButtonPressed()
    }

    private fun backButtonPressed() {
        val callback = object : OnBackPressedCallback(disableBackPressInSplashActivity) { override fun handleOnBackPressed() {} }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}