package com.dindinn.app.util

import android.annotation.SuppressLint
import android.content.Context
import com.dindinn.app.domain.model.DindinnOrder
import com.dindinn.app.util.constants.ConstantValues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.IOException
import java.io.InputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private fun getJsonFromAssets(context: Context): String? {
        return try {
            val `is`: InputStream = context.assets.open(ConstantValues.JSON_ORDER_NAME)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    @SuppressLint("CheckResult")
    fun getLocalOrders(context: Context): Single<DindinnOrder> {
        val jsonFileString: String =
            getJsonFromAssets(context) ?: ""

        var dindinnOrder: DindinnOrder? = null

        val gson = Gson()

        val dindinnOrderType = object : TypeToken<DindinnOrder>() {}.type

        dindinnOrder = gson.fromJson(jsonFileString, dindinnOrderType)

        return Single.just(dindinnOrder)
    }

    @SuppressLint("SimpleDateFormat")
    fun String.toMilliSeconds(): Long {
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy")
        return try {
            val mDate: Date = sdf.parse(this) ?: Date()
            mDate.time
        } catch (e: ParseException) {
            e.printStackTrace()
            0L
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun String.toSortTime(): String {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale(this))
        return try {
            val date1: Date = df.parse(this)
            val outputFormatter1: DateFormat = SimpleDateFormat("hh:mm aa")
            val output1: String = outputFormatter1.format(date1)
            output1
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
}