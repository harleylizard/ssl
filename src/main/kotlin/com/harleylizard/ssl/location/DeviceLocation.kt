package com.harleylizard.ssl.location

import com.google.gson.GsonBuilder
import com.harleylizard.ssl.SslExtension
import com.harleylizard.ssl.SslPlugin
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class DeviceLocation : Location {

    private fun publicIp(client: HttpClient, uri: URI): String {
        val request = HttpRequest.newBuilder(uri).GET().build()
        return client.send(request, HttpResponse.BodyHandlers.ofString())?.takeIf {
            it.statusCode() == OK
        }?.body() ?: throw RuntimeException("Failed to retrieve public ip address for device.")
    }

    override fun address(extension: SslExtension) = HttpClient.newHttpClient().use { client ->
        val uri = extension.geolocationApi.getOrElse(SslPlugin.geolocationApi)
        val publicIp = publicIp(client, extension.publicIpApi.getOrElse(SslPlugin.publicIpApi))

        val appendedUri = URI.create("$uri/$publicIp")
        val request = HttpRequest.newBuilder(appendedUri).GET().header("Accept", "application/json").build()

        client.send(request, HttpResponse.BodyHandlers.ofString())?.takeIf { it.statusCode() == OK }?.body()?.let { body ->
            val gson = GsonBuilder().registerTypeAdapter(Address::class.java, Address.deserialiser).create()
            gson.fromJson(body, Address::class.java)
        } ?: throw RuntimeException("")
    }

    companion object {
        private const val OK = 200

    }
}