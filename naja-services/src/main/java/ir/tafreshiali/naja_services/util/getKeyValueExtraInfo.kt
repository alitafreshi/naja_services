package ir.tafreshiali.naja_services.util

import com.google.gson.JsonParser
import ir.ayantech.pishkhancore.model.KeyValue

fun String.getKeyValueExtraInfo(): List<KeyValue> {
    if (this.isEmpty()) return arrayListOf()
    val result = ArrayList<KeyValue>()
    val extraInfoObject = JsonParser().parse(this).asJsonObject
    extraInfoObject.keySet().forEach {
        result.add(KeyValue(it, extraInfoObject[it].toString().replace("\"", "")))
    }
    return result
}