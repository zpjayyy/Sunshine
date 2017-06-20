//package com.jay.sunshine.tool
//
//import android.content.Context
//import android.support.v4.app.Fragment
//import android.widget.Toast
//
///**
// * Created by jay on 17/5/27.
// */
//inline fun Fragment.toast(message: Int): Unit = activity.toast(message)
//
//inline fun Context.toast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//inline fun Fragment.toast(message: CharSequence) = activity.toast(message)
//
//inline fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//inline fun Fragment.longToast(message: Int) = activity.longToast(message)
//
//
//inline fun Context.longToast(message: Int) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//
//
//inline fun Fragment.longToast(message: CharSequence) = activity.longToast(message)
//
///**
// * Display the simple toast message with the [Toast.LENGHT_LONG] duration
// *
// * @param message the message text.
// */
//inline fun Context.longToast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
//
//
//
