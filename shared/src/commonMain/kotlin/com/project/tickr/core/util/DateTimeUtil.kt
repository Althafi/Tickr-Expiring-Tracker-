package com.project.tickr.core.util

interface DateTimeUtil {
    fun isWithinDays(isoDate: String, days: Int): Boolean

    fun isPast(isoDate: String): Boolean

    fun today(): String
}
