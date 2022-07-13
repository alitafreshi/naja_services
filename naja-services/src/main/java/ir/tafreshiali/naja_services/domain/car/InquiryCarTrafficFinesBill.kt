package ir.tafreshiali.naja_services.domain.car

import ir.ayantech.pishkhancore.model.DateTime
import ir.ayantech.pishkhancore.model.InquiryHistory
import ir.ayantech.pishkhancore.model.NameShowName
import ir.tafreshiali.naja_services.domain.Advertisement
import ir.tafreshiali.naja_services.domain.BaseNajiResultModel
import ir.tafreshiali.naja_services.domain.PaymentExtraInfo

class InquiryCarTrafficFinesBillOutput(
    Result: TrafficFineBillResult,
    Advertisement: Advertisement?,
    Inquiry: InquiryHistory?,
    OtpRequired: Boolean?,
    InsufficientBalance: Boolean?,
    Terms: String?,
    Fee: Long?
) : BaseNajiResultModel<TrafficFineBillResult>(
    Result,
    Advertisement,
    Inquiry,
    OtpRequired,
    InsufficientBalance,
    Terms,
    Fee
)

data class TrafficFineBillResult(
    val Bills: List<TrafficFineBill>?,
    val TotalAmountOfPayableBills: Long,
    val TotalNumberOfPayableBills: Long
)

data class TrafficFineBill(
    val Detail: FineDetail,
    val ExtraInfo: String,
    val Header: Header,
    val PaymentExtraInfo: PaymentExtraInfo,
    val PaymentStatus: NameShowName
)

data class FineDetail(
    val City: String?,
    val ComplaintCode: String?,
    val ComplaintStatus: String?,
    val DateTime: DateTime?,
    val Delivery: String,
    val DeliveryIcon: String,
    val HasExtraDetail: Boolean,
    val Location: String,
    val SerialNumber: String,
    val PlateNumber: String,
    val Type: String,
    val Address: String?,
    val HasImage: Boolean,
    val TypeCode: String
)

data class Header(
    val Amount: Long,
    val Fee: Long,
    val TotalAmount: Long,
    val RecordNumber: String,
    val BillID: String?,
    val PaymentID: String?,
    val Type: NameShowName,
    val UniqueID: String
)