package com.example.melitest.core.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.melitest.R
import com.example.melitest.data.enums.Site
import com.example.melitest.data.model.RatingsModel
import com.example.melitest.data.model.SearchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.Serializable
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToInt


object Utils {

    fun convertMoney(site: String?, instance: String?, number: Float?): String {
        var response = ""
        var locale = Locale("pt", instance)

        if (site != null && instance != null && number != null) {

            when (site) {
                Site.MCO.toString(), Site.MLA.toString() -> locale = Locale("es", instance)
            }
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            numberFormat.minimumFractionDigits = 0
            response = numberFormat.format(number.roundToInt())
            response = response
        }
        return response
    }

    fun formatMessagePayments(number: Float?, context: Context): String? {
        val msg = context.getText(R.string.msg_payment)
        var response = ""
        if (number != null) {
            response = "${msg.toString().replace("%d", number?.roundToInt().toString())} "
        }
        return response
    }

    fun formatRating(ratingsModel: RatingsModel?): Float =
        ((ratingsModel?.positive!! + ratingsModel?.neutral!!) - ratingsModel?.negative!! / 3) * 5

    fun msgAvailable(site: String?, context: Context, number: Float?): String? {
        return getSpecificString(site, context, "msg_available")?.replace(
            "%d",
            number?.roundToInt().toString()
        )
    }

    fun hideKeyboard(view: View, context: Context) {
        if (view != null) {
            val inputMethodManager =
                context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun getSpecificString(site: String?, context: Context, key: String?): String {
        val resId = resIdByName("${key}_${site}", context)

        return context.getString(resId)
    }

    fun <T : Serializable?> getSerializable(intent: Intent, key: String, className: Class<T>): T {
        return if (Build.VERSION.SDK_INT >= 33)
            intent.getSerializableExtra(key, className)!!
        else
            intent.getSerializableExtra(key) as T
    }

    private fun resIdByName(resIdName: String?, context: Context): Int {
        resIdName?.let {
            return context.resources.getIdentifier(it, "string", context.packageName)
        }
        throw Resources.NotFoundException()
    }

    fun getSizeInDp(context: Context, r: Int?): Int {
        return ((context.resources.getDimension(r!!) / context.resources.displayMetrics.density).toInt())
    }

    fun getMockkForTest(context: Context): SearchModel {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("searchModel.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
        }

        val searchModel = object : TypeToken<SearchModel>() {}.type
        return Gson().fromJson(jsonString, searchModel)
    }

}