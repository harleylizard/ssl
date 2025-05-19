package com.harleylizard.ssl.location

import com.harleylizard.ssl.SslExtension
import java.net.http.HttpClient

@Suppress("CanSealedSubClassBeObject")
class DeviceLocation : Location {

    override fun address(extension: SslExtension) = HttpClient.newHttpClient().use {

        Address("", "", "")
    }
}