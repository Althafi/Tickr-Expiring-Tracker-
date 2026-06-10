package com.project.tickr.ui.theme

import androidx.compose.ui.graphics.Color

// Warna kategori donut — selaraskan dengan categories.color_hex di DB
// TODO(user): sesuaikan warna per kategori agar cocok dengan data DB
object DonutCategoryColors {
    val food = Color(0xFF0D6759)       // primaryBrand — Makanan & Minuman
    val beauty = Color(0xFFFA9A08)     // secondaryAccent — Kecantikan
    val medicine = Color(0xFF5EC9B7)   // teal muda — Obat & Vitamin
    val other = Color(0xFF6B7280)      // textSecondary — Lainnya

    fun fromHex(hex: String): Color = try {
        val cleaned = hex.removePrefix("#")
        Color(("FF$cleaned").toLong(16))
    } catch (_: Exception) { other }
}
