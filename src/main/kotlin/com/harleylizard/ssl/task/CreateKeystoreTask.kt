package com.harleylizard.ssl.task

import com.harleylizard.ssl.Keystore
import com.harleylizard.ssl.SslExtension
import com.harleylizard.ssl.location.Location
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.process.ExecOperations
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.inject.Inject

open class CreateKeystoreTask @Inject constructor(
    @Inject private val keystore: Keystore,
    @Inject private val name: String,
    @Inject private val exec: ExecOperations) : DefaultTask() {

    @TaskAction
    fun action() {
        val logger = project.logger
        logger.info("Creating keystore $name.")

        val extensions = project.extensions
        val extension = extensions.getByType(SslExtension::class.java)
        val address = keystore.location.orElse(Location.standard).get().address(extension)
        logger.info("City for address is ${address.city}")
        logger.info("State for address is ${address.state}")
        logger.info("Country for address is ${address.country}")

        val service = extensions.getByType(JavaToolchainService::class.java)
        val bin = service.launcherFor {}.get().metadata.installationPath.dir("bin").asFile.path
        logger.info("Using JDK path of $bin")

        val name = keystore.name.getOrElse("John Doe")
        val alias = keystore.alias.getOrElse(SslExtension.UNNAMED)
        val domain = keystore.domain.getOrElse("localhost")
        val password = keystore.password.getOrElse("password")
        val (city, state, country) = address

        val source = Paths.get(project.layout.buildDirectory.get().asFile.path).resolve("keystore").resolve(alias)
        logger.info("Keystore path $source.")

        val jks = source.resolve("keystore.jks").make.deleteIfExists.toString()

        exec.exec {
            val environment = it.environment
            val home: String? = System.getenv(JAVA_HOME)
            if (home.empty) {
                val path = environment["path", environment["Path", environment["PATH", ""]]]
                environment["Path"] = "$path;$home"
            }
            val args = "\"CN=$name, O=$domain, L=$city, ST=$state, C=$country\""
            it.commandLine(
                "keytool",
                "-genkeypair",
                "-alias", alias,
                "-keyalg", "RSA",
                "-keysize", "2048",
                "-validity", "365",
                "-keystore", jks,
                "-storepass", password,
                "-keypass", password,
                "-dname", args)
        }
    }

    companion object {
        private const val JAVA_HOME = "JAVA_HOME"

        private val Path.deleteIfExists get() = also(Files::deleteIfExists)

        private val Path.make get() = also { parent?.takeUnless { Files.isDirectory(it) }?.let { Files.createDirectories(it) } }

        private val String?.empty get() = isNullOrEmpty() || isBlank()

        private operator fun <K, V> Map<K, V>.get(k: K, d: V) = getOrDefault(k, d)

    }
}