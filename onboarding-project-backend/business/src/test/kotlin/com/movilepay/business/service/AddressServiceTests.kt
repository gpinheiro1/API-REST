package com.movilepay.business.service

import com.movilepay.business.model.Address
import com.movilepay.business.repository.AddressRepository
import com.movilepay.business.service.AddressFixture.getInvalidAddress
import com.movilepay.business.service.AddressFixture.getValidAddress
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import java.util.UUID

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressServiceTests {

    @MockK
    private lateinit var _mockedAddressRepository: AddressRepository

    private lateinit var addressService: AddressService

    @BeforeAll
    fun setUp() {
        MockKAnnotations.init(this)
        MockAddressRepository()
        addressService = AddressService(_mockedAddressRepository)
    }

    @Test
    fun `ensure Address model is returned when create request is right`() {
        val addressRequest = getValidAddress()
        val userId = UUID.randomUUID().toString()

        Assertions.assertNotNull(addressService.create(addressRequest, userId))
    }

    @Test
    fun `ensure IllegalArgumentException is handled when request doesn't contains a required field`() {
        val addressRequest = getInvalidAddress()
        val userId = UUID.randomUUID().toString()

        assertThrows<IllegalArgumentException> {
            addressService.create(addressRequest, userId)
        }
    }

    @Test
    fun `ensure Address model is returned when update request is right`() {
        val addressRequest = getValidAddress()

        Assertions.assertNotNull(addressService.updateAddress(addressRequest))
    }

    @Test
    fun `ensure IllegalArgumentException is handled when address is invalid for update`() {
        val addressRequest = getInvalidAddress()

        assertThrows<IllegalArgumentException> {
            addressService.updateAddress(addressRequest)
        }
    }

    @Test
    fun `ensure IllegalArgumentException is handled when invalid userId is sent for update `() {
        val addressRequest = getInvalidAddress()

        assertThrows<IllegalArgumentException> {
            addressService.updateAddress(addressRequest)
        }
    }

    private fun MockAddressRepository() {
        every { _mockedAddressRepository.create(any()) } returns (
                getValidAddress())

        every { _mockedAddressRepository.update(any()) } returns (
                getValidAddress())
    }

    private fun getValidAddress() : Address =
         Address(
            id = UUID.randomUUID().toString(),
            userId = UUID.randomUUID().toString(),
            city = "City Test",
            country = "Country Test",
            number = 1,
            state = "State Test",
            street = "Street Test",
            zipCode = "13000-000"
        )
    
    private fun getInvalidAddress() : Address =
         Address(
            id = UUID.randomUUID().toString(),
            userId = UUID.randomUUID().toString(),
            city = "",
            country = "",
            number = 1,
            state = "",
            street = "",
            zipCode = ""
        )
}