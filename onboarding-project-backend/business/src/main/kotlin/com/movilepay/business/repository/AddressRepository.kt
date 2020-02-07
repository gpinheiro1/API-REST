package com.movilepay.business.repository

import com.movilepay.business.model.Address

interface AddressRepository {
    fun create(address: Address): Address
    fun update(address: Address): Address
    fun deleteByUserId(userId: String)
}