package com.movilepay.webservice.exception

import com.movilepay.api.base.BaseErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ExceptionHandler(IllegalArgumentException::class)
    fun doResolveExceptionIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<BaseErrorResponse> {
        val errorResponse = BaseErrorResponse.fail(listOf(exception.message ?: ""))
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}