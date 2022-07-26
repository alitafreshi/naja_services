package ir.tafreshiali.naja_services.domain.passport_status

import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel

data class NajaServicesInquiryPassportStatusInput(
    val MobileNumber: String,
    val NationalCode: String
)

class NajaServicesInquiryPassportStatusOutput(
    Result: NajaServicesInquiryPassportStatusResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryPassportStatusResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class NajaServicesInquiryPassportStatusResult(
    val ExpirationDateTime: DateTime,
    val IssueDateTime: DateTime,
    val Number: String,
    val PostalBarcode: String,
    val RequestDateTime: DateTime,
    val RequestStatus: String,
    val Status: String
)