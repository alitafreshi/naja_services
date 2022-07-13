package ir.tafreshiali.naja_services.domain

import ir.ayantech.pishkhancore.model.DateTime

data class PaymentExtraInfo(
    val DateTime: DateTime,
    val TraceNumber: String
)