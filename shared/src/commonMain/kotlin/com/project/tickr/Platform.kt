package com.project.tickr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform