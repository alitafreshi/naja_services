package ir.tafreshiali.naja_services.domain.naja_negative_point

import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel

data class NajaServicesInquiryDrivingLicenseNegativePointInput(
    val LicenseNumber: String,
    val MobileNumber: String,
    val NationalCode: String
)

class NajaServicesInquiryDrivingLicenseNegativePointOutput(
    Result: NajaServicesInquiryDrivingLicenseNegativePointResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryDrivingLicenseNegativePointResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class NajaServicesInquiryDrivingLicenseNegativePointResult(
    val AllowedToDrive: Boolean,
    val Point: String,
    val Rule: String
)