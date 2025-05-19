package com.harleylizard.ssl

enum class Platform {
    WINDOWS,
    LINUX,
    MAC;

    companion object {
        private val String.platform: Platform get() = when {
            contains("win") -> WINDOWS
            contains("linux") -> LINUX
            contains("mac") -> MAC
            else -> throw RuntimeException("Unknown platform.")
        }

        private val platform = System.getProperty("os.name").lowercase().platform

        fun isOn(platform: Platform) = this.platform == platform

    }
}