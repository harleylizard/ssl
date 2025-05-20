package com.harleylizard.ssl

import com.harleylizard.ssl.location.Location
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import java.nio.file.Path

open class Keystore(val path: Path, val alias: String, objects: ObjectFactory) {
    val name: Property<String> = objects.property(String::class.java)
    val domain: Property<String> = objects.property(String::class.java)
    val password: Property<String> = objects.property(String::class.java)
    val location: Property<Location> = objects.property(Location::class.java)

}