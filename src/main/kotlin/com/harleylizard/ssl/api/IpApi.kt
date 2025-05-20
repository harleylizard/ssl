package com.harleylizard.ssl.api

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.harleylizard.ssl.location.Address
import java.net.URI
import java.net.http.HttpResponse

class IpApi : Api<Address> {
    override val uri: URI = URI.create("http://ip-api.com/json")

    override fun parse(response: HttpResponse<String>?) = response?.body()?.let {
        val gson = GsonBuilder().registerTypeAdapter(Address::class.java, deserialiser).create()
        gson.fromJson(it, Address::class.java)
    }

    companion object {
        private val deserialiser = JsonDeserializer { json, _, _ ->
            val obj = json.asJsonObject
            val city = obj.getAsJsonPrimitive("city").asString
            val state = obj.getAsJsonPrimitive("region").asString
            val country = obj.getAsJsonPrimitive("countryCode").asString
            Address(city, state, country)
        }
    }
}