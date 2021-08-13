package com.dindinn.app.util

import android.annotation.SuppressLint
import android.content.Context
import com.dindinn.app.util.constants.ConstantValues
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    @SuppressLint("SimpleDateFormat")
    fun String.toMilliSeconds(): Long {
        val df: DateFormat = SimpleDateFormat(ConstantValues.SIMPLE_DATE_FORMAT, Locale(this))
        return try {
            val mDate: Date = df.parse(this) ?: Date()
            mDate.time
        } catch (e: ParseException) {
            e.printStackTrace()
            0L
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun String.toShortTime(): String {
        val df: DateFormat = SimpleDateFormat(ConstantValues.SIMPLE_DATE_FORMAT, Locale(this))
        return try {
            val date1: Date = df.parse(this) ?: Date()
            val outputFormatter1: DateFormat = SimpleDateFormat("hh:mm aa")
            val output1: String = outputFormatter1.format(date1)
            output1
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }

    fun Context.readFileFromAssets(filePath: String): String {
        return resources.assets.open(filePath).bufferedReader().use {
            it.readText()
        }
    }
}