package ir.tafreshiali.naja_services.domain.car_Identification_doc_status

import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel

class NajaServicesInquiryCarIdentificationDocumentsStatusOutput(
    Result: NajaServicesInquiryCarIdentificationDocumentsStatusResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<NajaServicesInquiryCarIdentificationDocumentsStatusResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class NajaServicesInquiryCarIdentificationDocumentsStatusResult(
    val Card: Card,
    val Document: CarDocument,
    val PlateNumber: String
)

data class Card(
    val DateTime: DateTime,
    val PostalBarcode: String,
    val Title: String
)

data class CarDocument(
    val DateTime: DateTime,
    val Title: String
)