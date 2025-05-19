package com.harleylizard.test

import org.junit.jupiter.api.Test

class Test {

    @Test
    fun test() {
        println(System.getenv("JAVA_HOME"))
        println(System.getenv("java.home"))
    }
}