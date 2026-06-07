package com.project.tickr.core.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun provideHttpEngine(): HttpClientEngine = OkHttp.create {
    val context = ChuckerContext.applicationContext
        ?: error("ChuckerContext.applicationContext belum di-init di Application.onCreate()")
    addInterceptor(
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .build()
    )
}
