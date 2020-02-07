package com.movilepay.business.service

import com.movilepay.business.model.Address
import com.movilepay.business.repository.AddressRepository
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class AddressService(
    private val addressRepository: AddressRepository
) {

    fun create(address: Address, userId: String): Address {
        validateAddress(address, userId)
        return addressRepository.create(address)
    }

    fun deleteByUserId(userId: String){
        addressRepository.deleteByUserId(userId)
    }

    fun updateAddress(address: Address): Address {
        validateAddress(address)
        return addressRepository.update(address)
    }

    private fun validateAddress(address: Address, userId: String? = null) {
        require(!address.city.isNullOrEmpty() &&
                !address.country.isNullOrEmpty() &&
                !address.state.isNullOrEmpty() &&
                !address.street.isNullOrEmpty() &&
                !address.zipCode.isNullOrEmpty() &&
                address.number > 0){
            "Invalid request for address."
        }
        userId?.let {
            require(!it.isNullOrEmpty()) { "Invalid user ID" }
        }
    }

}
