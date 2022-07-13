package ir.tafreshiali.naja_services.domain.car

data class CarPlateNumber(
    val PlateNumberSection01: String,
    val PlateNumberSection02: String,
    val PlateNumberSection03: String?,
    val PlateNumberSection04: String?
) {

    /**اینا چرا تعریف شدن؟*/
    var CarTypeID: String? = null
    var VIN: String? = null
    var NationalCode: String? = null
    var EngineNumber: String? = null
    var TaxGroupID: String? = null
    var MobileNumber: String? = null

    fun toShowString(): String {
        return if (PlateNumberSection03 != null)
            "$PlateNumberSection04 - $PlateNumberSection03 $PlateNumberSection02 $PlateNumberSection01"
        else
            "$PlateNumberSection02 - $PlateNumberSection01"
    }
}