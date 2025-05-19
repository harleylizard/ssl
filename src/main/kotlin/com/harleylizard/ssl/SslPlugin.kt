package com.harleylizard.ssl

import com.harleylizard.ssl.api.Api
import com.harleylizard.ssl.api.IpApi
import com.harleylizard.ssl.api.IpifyApi
import com.harleylizard.ssl.location.Address
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

open class SslPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(JavaPlugin::class.java)

        val objects = target.objects
        target.extensions.create("ssl", SslExtension::class.java, target, objects)
    }

    companion object {
        const val GROUP = "ssl"

        val locationApi: Api<Address> = IpApi()
        val publicIpApi: Api<String> = IpifyApi()

    }
}