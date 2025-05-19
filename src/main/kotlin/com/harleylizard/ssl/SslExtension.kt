package com.harleylizard.ssl

import com.harleylizard.ssl.location.ExplicitLocation
import com.harleylizard.ssl.location.Location
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import java.net.URI
import javax.inject.Inject

open class SslExtension @Inject constructor(@Inject private val objects: ObjectFactory) {
    val api: Property<URI> = objects.property(URI::class.java)

    fun keystore(unit: Provider.() -> Unit) {
        unit(Provider(objects))
    }

    fun location(unit: ExplicitLocation.() -> Unit): Location = ExplicitLocation(objects).also(unit)
}


