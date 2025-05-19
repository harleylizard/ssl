package com.harleylizard.ssl

import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

open class SslExtension @Inject constructor(@Inject private val objects: ObjectFactory) {

    fun keystore(unit: Provider.() -> Unit) {
        unit(Provider(objects))
    }
}


