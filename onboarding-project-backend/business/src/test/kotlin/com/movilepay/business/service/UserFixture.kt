package com.movilepay.business.service

import com.movilepay.api.user.UserType
import com.movilepay.business.model.Address
import com.movilepay.business.model.User
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

object UserFixture {
    fun getValidUser(
        id: String = UUID.randomUUID().toString(),
        firstName: String = "First Name Test",
        lastName: String = "Last Name Test",
        company: String = "Company Test",
        email: String = "emailtest@gmail.com",
        birthDate: LocalDate = LocalDate.now().minus(20, ChronoUnit.DAYS),
        documentId: String = "123.456.789-12",
        type: UserType = UserType.USER,
        address: Address = AddressFixture.getValidAddress()
    ): User =
        User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            company = company,
            email = email,
            birthDate = birthDate,
            documentId = documentId,
            type = type,
            address = address
        )

    fun getInvalidUser(
        id: String = UUID.randomUUID().toString(),
        firstName: String = "",
        lastName: String = "",
        company: String = "",
        email: String = "",
        birthDate: LocalDate = LocalDate.now(),
        documentId: String = "",
        type: UserType = UserType.USER,
        address: Address = AddressFixture.getInvalidAddress()
    ): User =
        User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            company = company,
            email = email,
            birthDate = birthDate,
            documentId = documentId,
            type = type,
            address = address
        )
}