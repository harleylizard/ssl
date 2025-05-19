package com.harleylizard.ssl.api

import java.net.URI
import java.net.http.HttpResponse

interface Api<T> {
    val uri: URI

    fun parse(response: HttpResponse<String>?): T?

}