package com.movilepay.business.model

data class Address (
    val id: String,
    val country: String,
    val state: String,
    val city: String,
    val zipCode: String,
    val street: String,
    val number: Int,
    val userId: String
)