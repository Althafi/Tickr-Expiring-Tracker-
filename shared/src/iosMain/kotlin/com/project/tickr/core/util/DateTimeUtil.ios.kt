package com.project.tickr.core.util

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.timeIntervalSince1970

actual fun getTodayString(): String {
    val formatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd"
    }
    return formatter.stringFromDate(NSDate()) ?: ""
}

actual fun getNowEpochMillis(): Long = (NSDate().timeIntervalSince1970 * 1000).toLong()
