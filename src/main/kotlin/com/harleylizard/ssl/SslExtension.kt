package com.harleylizard.ssl

import com.harleylizard.ssl.location.ExplicitLocation
import com.harleylizard.ssl.location.Location
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import java.net.URI
import javax.inject.Inject

open class SslExtension @Inject constructor(@Inject private val objects: ObjectFactory) {
    val geolocationApi: Property<URI> = objects.property(URI::class.java)
    val publicIpApi: Property<URI> = objects.property(URI::class.java)

    val providers = mutableListOf<Provider>()

    fun keystore(unit: Provider.() -> Unit) {
        providers += Provider(objects).also(unit)
    }

    fun location(unit: ExplicitLocation.() -> Unit): Location = ExplicitLocation(objects).also(unit)
}


