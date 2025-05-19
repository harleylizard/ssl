package com.harleylizard.ssl.task

import com.harleylizard.ssl.Keystore
import com.harleylizard.ssl.SslExtension
import com.harleylizard.ssl.location.Location
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class CreateKeystoreTask @Inject constructor(
    @Inject private val keystore: Keystore,
    @Inject private val alias: String) : DefaultTask() {

    @TaskAction
    fun action() {
        val logger = project.logger
        val extension = project.extensions.getByType(SslExtension::class.java)
        logger.info("Creating keystore $alias.")
        val address = keystore.location.orElse(Location.standard).get().address(extension)

        logger.info("City for address is ${address.city}")
        logger.info("State for address is ${address.state}")
        logger.info("Country for address is ${address.country}")
    }
}