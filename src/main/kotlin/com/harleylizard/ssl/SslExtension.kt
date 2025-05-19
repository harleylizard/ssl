package com.harleylizard.ssl

import com.harleylizard.ssl.location.ExplicitLocation
import com.harleylizard.ssl.location.Location
import com.harleylizard.ssl.task.CreateKeystoreTask
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import java.net.URI
import javax.inject.Inject

open class SslExtension @Inject constructor(
    @Inject private val project: Project,
    @Inject private val objects: ObjectFactory) {

    val locationApi: Property<URI> = objects.property(URI::class.java) // not sussy, needed for keytool
    val publicIpApi: Property<URI> = objects.property(URI::class.java)

    fun keystore(unit: Keystore.() -> Unit) {
        val keystore = Keystore(objects).also(unit)
        val name = keystore.alias.getOrElse(UNNAMED).replaceFirstChar { it.uppercase() }
        project().tasks.register("createKeystore$name", CreateKeystoreTask::class.java, keystore, name).configure {
            it.group = SslPlugin.GROUP
        }
    }

    fun location(unit: ExplicitLocation.() -> Unit): Location = ExplicitLocation(objects).also(unit)

    fun project() = project

    companion object {
        const val UNNAMED = "unnamed"

    }
}


