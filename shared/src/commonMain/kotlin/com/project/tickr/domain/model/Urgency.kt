package com.project.tickr.domain.model

enum class Urgency {
    CRITICAL,  // <= 3 hari
    WARNING,   // 4-7 hari
    SAFE,      // > 7 hari
}

fun daysToUrgency(daysUntil: Int): Urgency = when {
    daysUntil <= 3 -> Urgency.CRITICAL
    daysUntil <= 7 -> Urgency.WARNING
    else -> Urgency.SAFE
}
