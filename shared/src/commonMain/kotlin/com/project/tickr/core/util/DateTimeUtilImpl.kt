package com.project.tickr.core.util

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

expect fun getTodayString(): String
expect fun getNowEpochMillis(): Long

class DateTimeUtilImpl : DateTimeUtil {

    override fun isWithinDays(isoDate: String, days: Int): Boolean = try {
        val date = LocalDate.parse(isoDate)
        val today = LocalDate.parse(getTodayString())
        val endDate = today.plus(DatePeriod(days = days))
        date >= today && date <= endDate
    } catch (_: Exception) { false }

    override fun isPast(isoDate: String): Boolean = try {
        val date = LocalDate.parse(isoDate)
        val today = LocalDate.parse(getTodayString())
        date < today
    } catch (_: Exception) { false }

    override fun today(): String = getTodayString()

    override fun daysUntil(isoDate: String): Int = try {
        val date = LocalDate.parse(isoDate)
        val today = LocalDate.parse(getTodayString())
        (date.toEpochDays() - today.toEpochDays()).toInt()
    } catch (_: Exception) { 0 }

    override fun nowEpochMillis(): Long = getNowEpochMillis()

    override fun formatDisplayDate(epochMillis: Long): String = try {
        val ldt = Instant.fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val dayName = when (ldt.dayOfWeek) {
            DayOfWeek.MONDAY -> "Senin"
            DayOfWeek.TUESDAY -> "Selasa"
            DayOfWeek.WEDNESDAY -> "Rabu"
            DayOfWeek.THURSDAY -> "Kamis"
            DayOfWeek.FRIDAY -> "Jumat"
            DayOfWeek.SATURDAY -> "Sabtu"
            else -> "Minggu"
        }
        val monthName = when (ldt.monthNumber) {
            1 -> "Januari"; 2 -> "Februari"; 3 -> "Maret"; 4 -> "April"
            5 -> "Mei"; 6 -> "Juni"; 7 -> "Juli"; 8 -> "Agustus"
            9 -> "September"; 10 -> "Oktober"; 11 -> "November"
            else -> "Desember"
        }
        "$dayName, ${ldt.dayOfMonth} $monthName ${ldt.year}"
    } catch (_: Exception) { "" }

    override fun formatDisplayTime(epochMillis: Long): String = try {
        val ldt = Instant.fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        "${ldt.hour.toString().padStart(2, '0')}:" +
            "${ldt.minute.toString().padStart(2, '0')}:" +
            ldt.second.toString().padStart(2, '0')
    } catch (_: Exception) { "--:--:--" }

    override fun isoDateFromMillis(epochMillis: Long): String = try {
        Instant.fromEpochMilliseconds(epochMillis)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date
            .toString()
    } catch (_: Exception) { "" }

    override fun isoFromDMY(day: Int, month: Int, year: Int): String? = try {
        LocalDate(year, month, day).toString()
    } catch (_: Exception) { null }

    override fun millisFromDMY(day: Int, month: Int, year: Int): Long? = try {
        LocalDate(year, month, day).toEpochDays().toLong() * 86_400_000L
    } catch (_: Exception) { null }
}
