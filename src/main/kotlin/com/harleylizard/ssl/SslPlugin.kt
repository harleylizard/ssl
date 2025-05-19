package com.harleylizard.ssl

import com.harleylizard.ssl.task.CreateKeystoreTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

open class SslPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(JavaPlugin::class.java)

        val group = "ssl"
        val tasks = target.tasks
        tasks.register("createKeystore", CreateKeystoreTask::class.java) {
            it.group = group
        }

        val objects = target.objects
        target.extensions.create("ssl", SslExtension::class.java, objects)
    }
}