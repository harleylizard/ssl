package com.harleylizard.ssl.api

import java.net.URI
import java.net.http.HttpResponse

class IpifyApi : Api<String> {
    override val uri: URI = URI.create("https://api.ipify.org")

    override fun parse(response: HttpResponse<String>?) = response?.body()
}