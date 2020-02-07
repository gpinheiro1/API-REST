package com.movilepay.webservice.extension

import com.movilepay.api.address.AddressResponse
import com.movilepay.api.user.UserDetailResponse
import com.movilepay.business.model.User

fun User.toUserDetailResponse() =
    UserDetailResponse(
        firstName = this.firstName,
        lastName = this.lastName,
        documentId = this.documentId,
        type = this.type,
        company = this.company,
        birthDate = this.birthDate,
        email = this.email,
        id = this.id,
        address = AddressResponse(
            id = this.address.id,
            zipCode = this.address.zipCode,
            street = this.address.street,
            state = this.address.state,
            number = this.address.number,
            country = this.address.country,
            city = this.address.city,
            userId = this.address.userId
        )
    )