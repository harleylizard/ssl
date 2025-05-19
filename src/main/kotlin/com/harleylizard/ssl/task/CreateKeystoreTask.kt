package com.harleylizard.ssl.task

import com.harleylizard.ssl.SslExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateKeystoreTask : DefaultTask() {

    @TaskAction
    fun action() {
        val extension = project.extensions.getByType(SslExtension::class.java)


    }
}