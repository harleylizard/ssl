package com.harleylizard.ssl

import com.harleylizard.ssl.location.ExplicitLocation
import com.harleylizard.ssl.location.Location
import com.harleylizard.ssl.task.CreateKeystoreTask
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import java.nio.file.Paths
import javax.inject.Inject

open class SslExtension @Inject constructor(
    @Inject private val keystores: KeystoreSet,
    @Inject private val project: Project,
    @Inject private val objects: ObjectFactory) {

    var locationApi = SslPlugin.locationApi // not sussy, needed for keytool
    var publicIpApi = SslPlugin.publicIpApi

    fun keystore(alias: String, unit: Keystore.() -> Unit) {
        val path = Paths.get(project.layout.buildDirectory.get().asFile.path).resolve("keystore").resolve(alias)
        val keystore = Keystore(path, alias, objects).also(unit)
        keystores += keystore
        val name = keystore.alias.replaceFirstChar { it.uppercase() }
        project().tasks.register("createKeystore$name", CreateKeystoreTask::class.java, keystore, name).configure {
            it.group = SslPlugin.GROUP
        }
    }

    fun location(unit: ExplicitLocation.() -> Unit): Location = ExplicitLocation(objects).also(unit)

    fun project() = project
}


