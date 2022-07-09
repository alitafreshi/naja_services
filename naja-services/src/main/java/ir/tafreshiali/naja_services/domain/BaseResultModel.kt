package ir.tafreshiali.naja_services.domain

import android.content.Context
import ir.ayantech.ayannetworking.api.AyanApi
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.ayantech.whygoogle.helper.formatAmount
import ir.ayantech.whygoogle.helper.openUrl
import ir.ayantech.whygoogle.helper.toJsonString
import ir.tafreshiali.naja_services.R
import ir.tafreshiali.naja_services.constance.Endpoints
import ir.tafreshiali.naja_services.presentation.bottomsheet.AcceptRulesBottomSheet
import ir.tafreshiali.naja_services.presentation.bottomsheet.OneInputBottomSheet

open class BaseResultModel<T>(
    val Result: T,
    val Advertisement: Advertisement?,
    val Inquiry: InquiryHistory?
)

open class BaseNajiResultModel<T>(
    Result: T,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    val OtpRequired: Boolean?,
    val InsufficientBalance: Boolean?,
    val Terms: String?,
    val Fee: Long?
) : BaseResultModel<T>(Result, Advertisement, Inquiry) {

    fun checkAndHandleStatus(
        ayanApi: AyanApi,
        context: Context,
        mobileNumber: String?,
        input: Any,
        product: String,
        checkCompleted: (T?) -> Unit
    ) {
        when {
            this@BaseNajiResultModel.OtpRequired == true -> {
                OneInputBottomSheet(
                    context = context,
                    title = context.resources.getString(R.string.tv_auth_title),
                    message = context.resources.getString(R.string.tv_entered_sended_code),
                    hint = context.resources.getString(R.string.sended_code_hint),
                    action = context.resources.getString(R.string.tv_agree_and_continue),
                    suggestions = null,
                    stringCallBack = {
                        ayanApi.simpleCall<Void>(
                            endPoint = Endpoints.MobileVerification,
                            input = MobileVerificationInput(mobileNumber!!, it)
                        ) {
                            checkCompleted(null)
                        }
                    }
                ).show()
            }
            this@BaseNajiResultModel.InsufficientBalance == true -> {
                AcceptRulesBottomSheet(
                    context = context,
                    rules = this@BaseNajiResultModel.Terms ?: "",
                    acceptText = "${context.resources.getString(R.string.tv_pay)} ${this@BaseNajiResultModel.Fee?.formatAmount()}"
                ) {
                    ayanApi.simpleCall<NajaServicesPaymentRequestOutput>(
                        Endpoints.NajaServicesPaymentRequest,
                        NajaServicesPaymentRequestInput(
                            NajiCallBackModel(
                                input.toJsonString(),
                                product
                            ).toJsonString(), product
                        )
                    ) {
                        it?.PaymentLink?.openUrl(context)
                    }
                }.show()
            }
            else -> checkCompleted(this@BaseNajiResultModel.Result)
        }
    }
}