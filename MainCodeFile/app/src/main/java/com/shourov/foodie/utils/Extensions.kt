package com.shourov.foodie.utils

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun Context.showSuccessToast(
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) = Toasty.success(this, message.toString(), duration, true).show()

fun Context.showErrorToast(
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) = Toasty.error(this, message.toString(), duration, true).show()

fun Context.showWarningToast(
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) = Toasty.warning(this, message.toString(), duration, true).show()

fun Context.showInfoToast(
    message: String?,
    duration: Int = Toast.LENGTH_SHORT
) = Toasty.info(this, message.toString(), duration, true).show()