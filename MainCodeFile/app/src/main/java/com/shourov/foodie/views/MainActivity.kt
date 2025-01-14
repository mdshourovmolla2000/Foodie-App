package com.shourov.foodie.views

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED
import androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shourov.foodie.R
import com.shourov.foodie.databinding.ActivityMainBinding
import com.shourov.foodie.databinding.DialogExitLayoutBinding
import com.shourov.foodie.utils.NavigationHelper
import com.shourov.foodie.utils.showSuccessToast
import com.shourov.foodie.view_model.SharedViewModel
import com.shourov.foodie.views.authPage.AuthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private var currentFragmentId = R.id.homeFragment

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        observerList()

        //this will run every time when any fragment changes
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentFragmentId = destination.id

            updateBottomNavigationIconColor()

            binding.apply {
                navigationViewMenuButton.visibility = if (currentFragmentId == R.id.homeFragment) View.VISIBLE else View.GONE

                drawerLayout.setDrawerLockMode(if (currentFragmentId == R.id.homeFragment) LOCK_MODE_UNLOCKED else LOCK_MODE_LOCKED_CLOSED)

                backButton.visibility = if (currentFragmentId == R.id.homeFragment) View.GONE else View.VISIBLE

                profilePic.visibility = if (currentFragmentId == R.id.homeFragment) View.VISIBLE else View.GONE

                bottomNavigationMenuLayout.visibility = when (currentFragmentId) {
                    R.id.homeFragment,
                    R.id.favoriteFragment,
                    R.id.cartFragment,
                    R.id.notificationFragment,
                    R.id.profileFragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }

        binding.apply {
            backButton.setOnClickListener { supportFragmentManager.popBackStack() }

            navigationViewMenuButton.setOnClickListener { drawerLayout.open() }

            navigationView.setNavigationItemSelectedListener {
                try {
                    drawerLayout.close()

                    when (it.itemId) {
                        R.id.navigationViewLogoutMenu -> logout()
                    }
                } catch (e: Exception) { e.printStackTrace() }

                return@setNavigationItemSelectedListener true
            }

            bottomNavigationHomeMenuIcon.setOnClickListener {
                if (currentFragmentId != R.id.homeFragment) { navController.popBackStack() }
            }

            bottomNavigationFavoriteMenuIcon.setOnClickListener {
                if (currentFragmentId == R.id.homeFragment) {
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_favoriteFragment)
                } else if(currentFragmentId != R.id.favoriteFragment) {
                    navController.popBackStack()
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_favoriteFragment)
                }
            }

            bottomNavigationCartMenuIcon.setOnClickListener {
                if (currentFragmentId == R.id.homeFragment) {
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_cartFragment)
                } else if(currentFragmentId != R.id.cartFragment) {
                    navController.popBackStack()
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_cartFragment)
                }
            }

            bottomNavigationNotificationMenuIcon.setOnClickListener {
                if (currentFragmentId == R.id.homeFragment) {
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_notificationFragment)
                } else if(currentFragmentId != R.id.notificationFragment) {
                    navController.popBackStack()
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_notificationFragment)
                }
            }

            bottomNavigationProfileMenuIcon.setOnClickListener {
                if (currentFragmentId == R.id.homeFragment) {
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_profileFragment)
                } else if(currentFragmentId != R.id.profileFragment) {
                    navController.popBackStack()
                    NavigationHelper.navigateTo(navController, R.id.action_homeFragment_to_profileFragment)
                }
            }
        }

        updateBottomNavigationIconColor()

        backButtonPressed()
    }

    private fun observerList() {
        sharedViewModel.titleLiveData.observe(this) {
            binding.titleTextview.text = it ?: getString(R.string.app_name)
        }
    }

    private fun updateBottomNavigationIconColor() {
        val selectedColor = ContextCompat.getColor(this@MainActivity, R.color.themeColor)
        val unSelectedColor = ContextCompat.getColor(this@MainActivity, R.color.grayColor)
        binding.apply {
            bottomNavigationHomeMenuIcon.setColorFilter(unSelectedColor)
            bottomNavigationFavoriteMenuIcon.setColorFilter(unSelectedColor)
            bottomNavigationCartMenuIcon.setColorFilter(unSelectedColor)
            bottomNavigationNotificationMenuIcon.setColorFilter(unSelectedColor)
            bottomNavigationProfileMenuIcon.setColorFilter(unSelectedColor)

            when (currentFragmentId) {
                R.id.homeFragment -> bottomNavigationHomeMenuIcon.setColorFilter(selectedColor)
                R.id.favoriteFragment -> bottomNavigationFavoriteMenuIcon.setColorFilter(selectedColor)
                R.id.cartFragment -> bottomNavigationCartMenuIcon.setColorFilter(selectedColor)
                R.id.notificationFragment -> bottomNavigationNotificationMenuIcon.setColorFilter(selectedColor)
                R.id.profileFragment -> bottomNavigationProfileMenuIcon.setColorFilter(selectedColor)
            }
        }
    }

    private fun logout() {
        showSuccessToast("Logged out successfully")
        val intent = Intent(this@MainActivity, SplashActivity::class.java)
        val options = ActivityOptions.makeCustomAnimation(this@MainActivity, R.anim.enter, R.anim.exit)
        startActivity(intent, options.toBundle())
        finish()
    }

    private fun backButtonPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (currentFragmentId == R.id.homeFragment) {
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.close()
                    } else {
                        val builder = AlertDialog.Builder(this@MainActivity)
                        val dialogBinding = DialogExitLayoutBinding.inflate(layoutInflater)

                        builder.setView(dialogBinding.root)
                        builder.setCancelable(true)

                        val alertDialog = builder.create()

                        //make transparent to default dialog
                        alertDialog.window?.setBackgroundDrawable(ColorDrawable(0))

                        dialogBinding.apply {
                            noButton.setOnClickListener { alertDialog.dismiss() }

                            yesButton.setOnClickListener {
                                alertDialog.dismiss()
                                finish()
                            }
                        }

                        alertDialog.show()
                    }
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }
}