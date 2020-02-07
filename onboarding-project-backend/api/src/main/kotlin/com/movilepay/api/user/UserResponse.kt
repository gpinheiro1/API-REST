package com.movilepay.api.user

import java.time.Instant
import java.time.LocalDate

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val company: String,
    val email: String,
    val birthDate: LocalDate,
    val documentId: String,
    val type: UserType
)