package ir.tafreshiali.naja_services.extension

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.whygoogle.helper.fromJsonToObject
import ir.tafreshiali.naja_services.callback.navigateToDestinationActivity
import ir.tafreshiali.naja_services.constance.Endpoints
import ir.tafreshiali.naja_services.constance.NajaConstance
import ir.tafreshiali.naja_services.constance.NajaGatewayConstance
import ir.tafreshiali.naja_services.domain.NajaServicesPaymentRequestDataInput
import ir.tafreshiali.naja_services.domain.NajaServicesPaymentRequestDataOutput
import ir.tafreshiali.naja_services.domain.NajiCallBackModel


/**
 * We should usually use this method  in  [AppCompatActivity.onCreate] or in the [AppCompatActivity.onNewIntent]
 * here at first we check if the [Intent] has the [NajaConstance.NAJA_RESET_FLAG] or not . based on the condition we decide to recreate activity and return or continue the the process
 * the we check if the [Intent] has the [NajaConstance.NAJA_CALL_BACK_PRODUCT] Flag or not. in this case we should handle two scenarios:
 * 1 - If the stated condition is true, we must check that if the [NajaConstance.NAJA_CALL_BACK_PRODUCT] equals to [NajaConstance.TRAFFIC_BY_PLATE] or not .if they are equal we should call [onSuccessGatewayPayment] and pass the required information to the lambda function.
 * 2 - Don't do anything
 * @param failedDestinationActivity the destination activity that app should restart from it.
 * @param onSuccessGatewayPayment for handling Whenever the payment is successful.
 * */

fun <T> AppCompatActivity.handleCallBack(
    failedDestinationActivity: Class<T>,
    onSuccessGatewayPayment: (Intent) -> Unit
) {
    intent?.let {

        if (it.getBooleanExtra(NajaConstance.NAJA_RESET_FLAG, false)) {
            navigateToDestinationActivity(failedDestinationActivity)
            return
        }


        when (it.getStringExtra(NajaConstance.NAJA_CALL_BACK_PRODUCT)) {
            NajaConstance.TRAFFIC_BY_PLATE -> {
                if (it.getStringExtra(NajaGatewayConstance.PAYMENT_STATUS) == NajaGatewayConstance.SUCCESS_STATUS)
                    onSuccessGatewayPayment(it)
            }
        }
    }
}


/**
 * we use this function whenever the payment is successful. (call it in the [onSuccessGatewayPayment] of [handleCallBack] function)
 * @param failedDestinationActivity the destination activity that app should restart from it.
 * @param [product] [ayanApi]
 * @param handleNajaRelatedLogic a lambda function for handling the naja related logic. */
inline fun <reified Input, T> AppCompatActivity.handleSuccessfulPayment(
    failedDestinationActivity: Class<T>,
    product: String,
    ayanApi: AyanApi,
    crossinline handleNajaRelatedLogic: (input: Input) -> Unit
) {
    intent?.let {
        it.getStringExtra(NajaConstance.KEY)?.let { gateWayKey ->
            ayanApi.simpleCall<NajaServicesPaymentRequestDataOutput>(
                endPoint = Endpoints.NajaServicesPaymentRequestData,
                input = NajaServicesPaymentRequestDataInput(gateWayKey),
                onSuccess = { najaServicesPaymentDataOutput ->
                    val najiCallBackModel =
                        najaServicesPaymentDataOutput?.Data?.fromJsonToObject<NajiCallBackModel>()

                    when (najiCallBackModel?.product) {
                        product -> {
                            handleNajaRelatedLogic(najiCallBackModel.input.fromJsonToObject<Input>())
                        }
                        else -> {
                            navigateToDestinationActivity(failedDestinationActivity)
                        }
                    }
                })
        }
    }
}