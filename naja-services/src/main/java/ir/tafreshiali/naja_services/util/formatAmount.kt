package ir.tafreshiali.naja_services.util

import android.content.Context
import ir.tafreshiali.naja_services.R


fun String.formatAmount(isNegative: Boolean = false, context: Context): String {

    //val currency = if (General.appLanguageCode == "en") "Rials" else "ریال"
    val currency = context.getString(R.string.tv_unit)
    if (this.contains("-")) {
        return this.replace("-", "")
            .formatAmount(isNegative = true, context = context)
    }
    var amountString = this
    var mod = amountString.length % 3
    if (mod == 0)
        mod = 3
    while (mod < amountString.length) {
        amountString =
            amountString.substring(0, mod) + "," + amountString.substring(
                mod,
                amountString.length
            )
        mod += 4
    }
    val negativeCare = if (isNegative) " -" else ""
    amountString = "$amountString$negativeCare $currency "
    if (this == "0 $currency") amountString.replace("0", "zero")
    return amountString.trim()
}


fun Long.formatAmount(context: Context): String {
    return this.toString().formatAmount(context=context)
}