package com.shourov.foodie.utils

import android.os.Bundle
import androidx.navigation.NavController

object NavigationHelper {
    fun navigateTo(navController: NavController, destinationId: Int, bundle: Bundle? = null) {
        try { navController.navigate(destinationId, bundle) } catch (e: Exception) { e.printStackTrace() }
    }
}