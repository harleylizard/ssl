package com.harleylizard.ssl

import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.nio.file.Path

class KeystoreSet {
    private val set = mutableSetOf<Path>()

    internal operator fun plusAssign(keystore: Keystore) {
        set.add(keystore.path.resolve("keystore.jks"))
    }

    companion object {

        fun MavenArtifactRepository.selfSignedCertificates() {
        }
    }
}