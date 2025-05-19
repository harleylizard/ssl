package com.harleylizard.ssl.location

import com.harleylizard.ssl.SslExtension

sealed interface Location {

    fun address(extension: SslExtension): Address

    companion object {
        val standard = DeviceLocation()

    }
}