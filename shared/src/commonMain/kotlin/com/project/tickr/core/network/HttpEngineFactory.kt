package com.project.tickr.core.network

import io.ktor.client.engine.HttpClientEngine

expect fun provideHttpEngine(): HttpClientEngine
