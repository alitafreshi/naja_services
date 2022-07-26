package ir.tafreshiali.naja_services.domain.exit_ban

import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel

data class NajaServicesInquiryExitBanStatusInput(
    val MobileNumber: String,
    val NationalCode: String
)

class NajaServicesInquiryExitBanStatusOutput(
    Result: NajaServicesInquiryExitBanStatusResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryExitBanStatusResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)
data class NajaServicesInquiryExitBanStatusResult(
    val Allowed: Boolean,
    val Description: String
)