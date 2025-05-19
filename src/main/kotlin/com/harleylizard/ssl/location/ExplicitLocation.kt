package com.harleylizard.ssl.location

import com.harleylizard.ssl.SslExtension
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

@Suppress("MemberVisibilityCanBePrivate")
class ExplicitLocation(objects: ObjectFactory) : Location {
    val city: Property<String> = objects.property(String::class.java)
    val state: Property<String> = objects.property(String::class.java)
    val country: Property<String> = objects.property(String::class.java)

    override fun address(extension: SslExtension) = Address(
        city.getOrElse(""),
        state.getOrElse(""),
        country.getOrElse("")
    )
}