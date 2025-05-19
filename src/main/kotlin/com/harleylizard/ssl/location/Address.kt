package com.harleylizard.ssl.location

import com.google.gson.JsonDeserializer

class Address(
    val city: String,
    val state: String,
    val country: String) {

    companion object {
        val deserialiser = JsonDeserializer { json, typeOfT, context ->

            Address("", "", "")
        }

    }
}