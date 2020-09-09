package com.exalture.atm

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Take the Long milliseconds returned by the system and stored in Room,
 * and convert it to a nicely formatted string for display.
 *
 * MMM - Display the letter abbreviation of the nmotny
 * dd yyyy - day in month and full year numerically
 */
@SuppressLint("SimpleDateFormat")
fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("dd MMM yyyy").format(systemTime).toString()
}

/**
 * Take the Long milliseconds returned by the system and stored in Room,
 * and convert it to a nicely formatted string for display.
 *
 * hh:mm - Hours and minutes in 24hr format
 */
@SuppressLint("SimpleDateFormat")
fun convertLongToTimeString(systemTime: Long): String {
    return SimpleDateFormat("hh:mm aaa").format(systemTime).toString()
}