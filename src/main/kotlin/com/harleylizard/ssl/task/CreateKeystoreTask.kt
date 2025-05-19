package com.harleylizard.ssl.task

import com.harleylizard.ssl.SslExtension
import com.harleylizard.ssl.location.Location
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateKeystoreTask : DefaultTask() {

    @TaskAction
    fun action() {
        val logger = project.logger
        val extension = project.extensions.getByType(SslExtension::class.java)
        extension.keystores.forEach { keystore ->
            logger.info("Creating keystore.")
            val address = keystore.location.orElse(Location.standard).get().address(extension)

            logger.info("City for address is ${address.city}")
            logger.info("State for address is ${address.state}")
            logger.info("Country for address is ${address.country}")
        }
    }
}