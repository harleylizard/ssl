package com.harleylizard.ssl.location

sealed interface Location {

    val city: String

    val state: String

    val country: String

    companion object {
        val standard = DeviceLocation()

    }
}