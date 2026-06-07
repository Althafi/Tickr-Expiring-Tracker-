package com.project.tickr.domain.error

import com.project.tickr.core.result.AppError

class ErrorMessageProvider {
    fun provide(error: AppError): String = when (error) {
        is AppError.Network -> "Tidak ada koneksi. Coba lagi."
        is AppError.Unauthorized -> "Sesi berakhir. Silakan masuk kembali."
        is AppError.NotFound -> "${error.entity} tidak ditemukan."
        is AppError.Serialization -> "Gagal memproses data."
        is AppError.Validation -> error.reason
        is AppError.Unknown -> "Terjadi kesalahan. Coba lagi."
    }
}
