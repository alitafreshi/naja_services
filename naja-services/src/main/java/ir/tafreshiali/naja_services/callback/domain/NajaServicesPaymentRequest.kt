package ir.tafreshiali.naja_services.callback.domain

data class NajaServicesPaymentRequestInput(
    val Data: String,
    val ServiceName: String
)

data class NajaServicesPaymentRequestOutput(
    val PaymentKey: String,
    val PaymentLink: String
)