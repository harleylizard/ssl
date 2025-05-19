package com.harleylizard.ssl

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import java.net.URI

open class SslPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(JavaPlugin::class.java)

        val objects = target.objects
        target.extensions.create("ssl", SslExtension::class.java, target, objects)
    }

    companion object {
        const val GROUP = "ssl"

        val locationApi: URI = URI.create("http://ip-api.com/json")
        val publicIpApi: URI = URI.create("https://api.ipify.org")

    }
}