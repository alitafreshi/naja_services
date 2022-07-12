package ir.tafreshiali.naja_services.domain.license_status

import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel

data class NajaServicesInquiryDrivingLicenseStatusInput(
    val MobileNumber: String,
    val NationalCode: String
)

class NajaServicesInquiryDrivingLicenseStatusOutput(
    Result: NajaServicesInquiryDrivingLicenseStatusResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryDrivingLicenseStatusResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class NajaServicesInquiryDrivingLicenseStatusResult(
    val Licenses: List<License>
)

data class License(
    val Barcode: String,
    val DateTime: DateTime,
    val FirstName: String,
    val LastName: String,
    val NationalCode: String,
    val Number: String,
    val Status: String,
    val Type: String,
    val ValidityPeriodInYears: String
)