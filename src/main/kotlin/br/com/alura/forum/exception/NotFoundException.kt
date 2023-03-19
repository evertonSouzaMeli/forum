package br.com.alura.forum.exception

class NotFoundException(override val message: String?): RuntimeException(message) {
}