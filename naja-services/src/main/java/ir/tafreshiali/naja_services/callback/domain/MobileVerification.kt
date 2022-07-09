package ir.tafreshiali.naja_services.callback.domain

data class MobileVerificationInput(
    val MobileNumber: String,
    val VerificationCode: String
)