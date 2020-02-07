package com.movilepay.api.address

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AddressRequest(
    val country: String,
    val state: String,
    val city: String,
    val zipCode: String,
    val street: String,
    val number: Int
)