package com.harleylizard.ssl

import com.harleylizard.ssl.location.Location
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

open class Keystore(objects: ObjectFactory) {
    val name: Property<String> = objects.property(String::class.java)
    val alias: Property<String> = objects.property(String::class.java)
    val domain: Property<String> = objects.property(String::class.java)
    val password: Property<String> = objects.property(String::class.java)
    val location: Property<Location> = objects.property(Location::class.java)

}