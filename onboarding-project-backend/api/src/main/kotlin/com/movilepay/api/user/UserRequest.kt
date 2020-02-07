package com.movilepay.api.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.movilepay.api.address.AddressRequest
import java.time.Instant
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserRequest(
    val firstName: String,
    val lastName: String,
    val company: String,
    val email: String,
    val birthDate: LocalDate,
    val documentId: String,
    val type: UserType,
    var address: AddressRequest
)