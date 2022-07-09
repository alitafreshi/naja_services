package ir.tafreshiali.naja_services.callback.callback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ir.tafreshiali.naja_services.callback.constance.NajaConstance

fun  <T> AppCompatActivity.handleNajaGatewayCallBackUrl(
    url: String,
    destinationActivity: Class<T>
) {


    val items = url.substringAfter("?").split("&")

    val product = items.firstOrNull { it.startsWith("product") }?.split("=")?.get(1)

    val key = items.firstOrNull { it.startsWith("key") }?.split("=")?.get(1)

    if (product == NajaConstance.TRAFFIC_BY_PLATE) {

        startActivity(Intent(this,destinationActivity).also { intent ->
            intent.putExtra(NajaConstance.NAJA_CALL_BACK_PRODUCT, product)
            intent.putExtra(NajaConstance.KEY, key)
        })


    } else {
        finish()
        startActivity(Intent(this, destinationActivity))
    }
}