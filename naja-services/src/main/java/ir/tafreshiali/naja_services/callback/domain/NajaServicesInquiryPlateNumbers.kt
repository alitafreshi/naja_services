package ir.tafreshiali.naja_services.callback.domain

import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory

data class NajaServicesInquiryPlateNumbersInput(
    val MobileNumber: String,
    val NationalCode: String
)

class NajaServicesInquiryPlateNumbersOutput(
    Result: NajaServicesInquiryPlateNumbersResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryPlateNumbersResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class NajaServicesInquiryPlateNumbersResult(
    val PlateNumbers: List<UserPlateNumber>
)

data class UserPlateNumber(
    val NationalCode: String,
    val PlateNumber: String,
    val Revoked: Boolean,
    val RevokedDetail: RevokedDetail?,
    val SerialNumber: String
)

data class RevokedDetail(
    val DateTime: DateTime,
    val Description: String
)