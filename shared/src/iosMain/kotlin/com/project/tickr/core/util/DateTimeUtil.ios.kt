package com.project.tickr.core.util

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter

actual fun getTodayString(): String {
    val formatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd"
    }
    return formatter.stringFromDate(NSDate()) ?: ""
}
