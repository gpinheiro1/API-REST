package com.movilepay.business.model

import com.movilepay.api.user.UserType
import java.time.Instant
import java.time.LocalDate

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val company: String,
    val email: String,
    val birthDate: LocalDate,
    val documentId: String,
    val type: UserType,
    val address: Address
)