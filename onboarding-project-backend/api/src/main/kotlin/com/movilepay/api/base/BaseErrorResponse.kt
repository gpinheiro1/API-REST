package com.movilepay.api.base

open class BaseErrorResponse(
    val messages: List<String>
) {
    companion object {
        fun fail(
            messages: List<String> = emptyList()
        ): BaseErrorResponse {
            return BaseErrorResponse(messages)
        }
    }
}