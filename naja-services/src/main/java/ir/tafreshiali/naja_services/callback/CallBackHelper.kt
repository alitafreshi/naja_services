package ir.tafreshiali.naja_services.callback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ir.tafreshiali.naja_services.constance.NajaConstance

/**Naja Gateway Call Back
 * steps goes like down =
 * 1 - first we use [substringAfter] with (?) [delimiter] and [split] the url with (&)
 * 2 - then we are searching for (product) Keyword and [split] it with (=) [delimiter] in the founded url in first step.
 * 3 - then we are searching for (key) Keyword and [split] it with (=) [delimiter] in the founded url in first step.
 * 4 - if the value of the (product) Keyword that founds in step 2 contains [NajaConstance.TRAFFIC_BY_PLATE] we are passing the required information with in an intent */
fun <T> AppCompatActivity.handleNajaGatewayCallBackUrl(
    destinationActivity: Class<T>
) {
    intent.data?.toString()?.let { url ->

        val items = url.substringAfter("?").split("&")

        val product = items.firstOrNull { it.startsWith("product") }?.split("=")?.get(1)

        val key = items.firstOrNull { it.startsWith("key") }?.split("=")?.get(1)

        if (product == NajaConstance.TRAFFIC_BY_PLATE) {

            startActivity(Intent(this, destinationActivity).also { intent ->
                intent.putExtra(NajaConstance.NAJA_CALL_BACK_PRODUCT, product)
                intent.putExtra(NajaConstance.KEY, key)
            })


        } else {
            finish()
            startActivity(Intent(this, destinationActivity))
        }
    }
}