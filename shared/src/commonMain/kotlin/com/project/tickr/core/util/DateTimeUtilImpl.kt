package com.project.tickr.core.util

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

expect fun getTodayString(): String

class DateTimeUtilImpl : DateTimeUtil {

    override fun isWithinDays(isoDate: String, days: Int): Boolean {
        return try {
            val date = LocalDate.parse(isoDate)
            val today = LocalDate.parse(getTodayString())
            val endDate = today.plus(DatePeriod(days = days))
            date >= today && date <= endDate
        } catch (e: Exception) {
            false
        }
    }

    override fun isPast(isoDate: String): Boolean {
        return try {
            val date = LocalDate.parse(isoDate)
            val today = LocalDate.parse(getTodayString())
            date < today
        } catch (e: Exception) {
            false
        }
    }

    override fun today(): String {
        return getTodayString()
    }
}
