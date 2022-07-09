package ir.tafreshiali.naja_services.domain

data class MobileVerificationInput(
    val MobileNumber: String,
    val VerificationCode: String
)