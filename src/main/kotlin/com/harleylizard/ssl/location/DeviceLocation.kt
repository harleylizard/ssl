package com.harleylizard.ssl.location

import com.harleylizard.ssl.SslExtension
import com.harleylizard.ssl.api.Api
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class DeviceLocation : Location {

    private fun publicIp(client: HttpClient, api: Api<String>): String {
        val request = HttpRequest.newBuilder(api.uri).GET().build()
        return api.parse(client.send(request, HttpResponse.BodyHandlers.ofString())?.takeIf {
            it.statusCode() == OK
        }) ?: throw RuntimeException("Failed to retrieve public-ip address for device.")
    }

    override fun address(extension: SslExtension) = HttpClient.newHttpClient().use { client ->
        val api = extension.locationApi
        val uri = api.uri

        val publicIp = publicIp(client, extension.publicIpApi)
        val logger = extension.project().logger
        logger.info("Using public-ip address: $publicIp.")

        val appendedUri = URI.create("$uri/$publicIp")
        logger.info("Appended api $appendedUri")
        val request = HttpRequest.newBuilder(appendedUri).GET().header("Accept", "application/json").build()

        api.parse(client.send(request, HttpResponse.BodyHandlers.ofString())?.takeIf { it.statusCode() == OK })
            ?: throw RuntimeException("Failed to retrieve location for keytool.")
    }

    companion object {
        private const val OK = 200

    }
}