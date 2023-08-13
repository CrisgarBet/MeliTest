package com.example.melitest.core.utils

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.example.melitest.R
import com.example.melitest.data.enums.Site
import com.example.melitest.data.model.RatingsModel
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
        return "${msg.toString().replace("%d", number?.roundToInt().toString())} "
    }

    fun formatRating(ratingsModel: RatingsModel?): Float =
        ((ratingsModel?.positive!! + ratingsModel?.neutral!!) - ratingsModel?.negative!! / 3) * 5

    fun msgShipping(site: String?, context: Context): String {
        var msg = context.getString(R.string.msg_shipping_pt)
        when (site) {
            Site.MCO.toString(), Site.MLA.toString() -> msg =
                context.getString(R.string.msg_shipping_es)
        }
        return msg
    }

    fun msgAvailable(site: String?, context: Context, number: Float?): String? {
        var msg = context.getString(R.string.msg_available_pt)
        when (site) {
            Site.MCO.toString(), Site.MLA.toString() -> msg =
                context.getString(R.string.msg_available_es)
        }
        return msg?.replace("%d", number?.roundToInt().toString())
    }

    fun hideKeyboard(view: View, context: Context) {
        if (view != null) {
            val inputMethodManager =
                context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun msgPlaceholder(site: String?, context: Context): String {
        var msg = context.getString(R.string.msg_placeholder_pt)
        when (site) {
            Site.MCO.toString(), Site.MLA.toString() -> msg =
                context.getString(R.string.msg_placeholder_es)
        }
        return msg
    }
}