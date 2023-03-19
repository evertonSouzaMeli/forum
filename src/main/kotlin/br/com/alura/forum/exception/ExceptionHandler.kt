package br.com.alura.forum.exception

import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException, request: HttpServletRequest ): ErrorHandler =
        ErrorHandler(
            status = NOT_FOUND.value(),
            error = NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )

    @ExceptionHandler(Exception::class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception, request: HttpServletRequest ): ErrorHandler =
        ErrorHandler(
            status = NOT_FOUND.value(),
            error = NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleValidationError(exception: MethodArgumentNotValidException, request: HttpServletRequest ): ErrorHandler {
        val errorMessage = HashMap<String, String?>()

        exception.bindingResult.fieldErrors.forEach{ errorMessage.put(it.field, it.defaultMessage) }

           return ErrorHandler(
                status = BAD_REQUEST.value(),
                error = BAD_REQUEST.name,
                message = errorMessage.toString(),
                path = request.servletPath
            )
    }
}