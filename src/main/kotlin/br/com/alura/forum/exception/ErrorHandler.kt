package br.com.alura.forum.exception

import java.time.LocalDateTime

data class ErrorHandler (
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)