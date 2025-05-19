package com.harleylizard.ssl

import com.harleylizard.ssl.location.ExplicitLocation
import com.harleylizard.ssl.location.Location
import com.harleylizard.ssl.task.CreateKeystoreTask
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.jvm.toolchain.JavaToolchainService
import java.net.URI
import javax.inject.Inject

open class SslExtension @Inject constructor(
    @Inject private val project: Project,
    @Inject private val objects: ObjectFactory) {

    val bin: String get() {
        val service = project.extensions.getByType(JavaToolchainService::class.java)
        val path = service.launcherFor {}.get().metadata.installationPath
        return path.dir("bin").asFile.path
    }

    val locationApi: Property<URI> = objects.property(URI::class.java)
    val publicIpApi: Property<URI> = objects.property(URI::class.java)

    fun keystore(unit: Keystore.() -> Unit) {
        val keystore = Keystore(objects).also(unit)
        val alias = keystore.alias.getOrElse(UNNAMED).replaceFirstChar { it.uppercase() }
        project().tasks.register("createKeystore$alias", CreateKeystoreTask::class.java, keystore, alias).configure {
            it.group = SslPlugin.GROUP
        }
    }

    fun location(unit: ExplicitLocation.() -> Unit): Location = ExplicitLocation(objects).also(unit)

    fun project() = project

    companion object {
        private const val UNNAMED = "unnamed"

        private const val JAVA_HOME = "JAVA_HOME"

    }
}


