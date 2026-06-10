package com.project.tickr.core.util

import java.time.LocalDate

actual fun getTodayString(): String {
    return LocalDate.now().toString()
}

actual fun getNowEpochMillis(): Long = System.currentTimeMillis()
