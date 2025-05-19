package com.harleylizard.ssl.location

import com.google.gson.JsonDeserializer

class Address(
    val city: String,
    val state: String,
    val country: String) {

    companion object {
        val deserialiser = JsonDeserializer { json, typeOfT, context ->
            val obj = json.asJsonObject
            val city = obj.getAsJsonPrimitive("city").asString
            val state = obj.getAsJsonPrimitive("region").asString
            val country = obj.getAsJsonPrimitive("countryCode").asString
            Address(city, state, country)
        }

    }
}