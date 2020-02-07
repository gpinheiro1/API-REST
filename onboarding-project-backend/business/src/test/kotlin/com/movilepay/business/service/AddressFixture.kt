package com.movilepay.business.service

import com.movilepay.business.model.Address
import java.util.*

object AddressFixture {
    fun getValidAddress(
        id: String = UUID.randomUUID().toString(),
        city: String = "City Test",
        country: String = "Country Test",
        number: Int = 1,
        state: String = "State Test",
        street: String = "Street Test",
        zipCode: String = "13000-000"
    ): Address =
        Address(
            id = id,
            userId = UUID.randomUUID().toString(),
            city = city,
            country = country,
            number = number,
            state = state,
            street = street,
            zipCode = zipCode
        )

    fun getInvalidAddress
                (id: String = UUID.randomUUID().toString(),
                 userId: String = UUID.randomUUID().toString(),
                 city: String = "",
                 country: String = "",
                 number: Int = 1,
                 state: String = "",
                 street: String = "",
                 zipCode: String = ""
    ): Address =
        Address(
        id = id,
        userId = userId,
        city = city,
        country = country ,
        number = number,
        state = state,
        street = street,
        zipCode = zipCode
    )
}