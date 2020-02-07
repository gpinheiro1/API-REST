package com.movilepay.parser

import com.movilepay.api.address.AddressRequest
import com.movilepay.api.user.UserRequest
import com.movilepay.api.user.UserType
import com.movilepay.business.model.Address
import com.movilepay.business.model.User
import com.movilepay.webservice.parser.UserParser
import io.mockk.MockKAnnotations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserParserTests {

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `ensure parseToUserResponse return a valid response`() {
        val response = UserParser.parseToUserResponse(getValidUserModel())
        Assertions.assertNotNull(response)
    }

    @Test
    fun `ensure parseToUserDetailResponse return a valid response`() {
        val response = UserParser.parseToUserDetailResponse(getValidUserModel())
        Assertions.assertNotNull(response)
    }

    @Test
    fun `ensure parseToUserModel return a valid model with no userId`() {
        val response = UserParser.parseToUserModel(getValidUserRequest())
        Assertions.assertNotNull(response)
    }

    @Test
    fun `ensure parseToUserModel return a valid model with userId provided `() {

    }

    fun getValidUserRequest(): UserRequest =
        UserRequest(
            email = "teste@mail.com",
            lastName = "Test",
            firstName = "First",
            type = UserType.ADMIN,
            company = "Test",
            birthDate = LocalDate.now(),
            documentId = "41500000001",
            address = AddressRequest(
                city = "City Test",
                country = "Country Test",
                number = 1,
                state = "State Test",
                street = "Street Test",
                zipCode = "13000-000"
            )
        )

    fun getValidUserModel(): User =
        User(
            id = UUID.randomUUID().toString(),
            email = "teste@mail.com",
            lastName = "Test",
            firstName = "First",
            type = UserType.ADMIN,
            company = "Test",
            birthDate = LocalDate.now(),
            documentId = "41500000001",
            address = Address(
                id = UUID.randomUUID().toString(),
                userId = UUID.randomUUID().toString(),
                city = "City Test",
                country = "Country Test",
                number = 1,
                state = "State Test",
                street = "Street Test",
                zipCode = "13000-000"
            )
        )


}