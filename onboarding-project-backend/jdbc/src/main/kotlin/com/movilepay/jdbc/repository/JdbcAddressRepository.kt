package com.movilepay.jdbc.repository

import com.movilepay.business.model.Address
import com.movilepay.business.repository.AddressRepository
import com.movilepay.jdbc.repository.sql.JdbcAddressRepositorySQL
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class JdbcAddressRepository (
    private val jdbcTemplate: JdbcTemplate) : AddressRepository {

    override fun deleteByUserId(userId: String) {
       namedTemplate.update(JdbcAddressRepositorySQL.DELETE_BY_USER,
           mapOf(
               "user_id" to userId
           ))
    }

    private val namedTemplate by lazy {
        NamedParameterJdbcTemplate(jdbcTemplate)
    }

    override fun create(address: Address): Address {
        namedTemplate.update(JdbcAddressRepositorySQL.INSERT_ADDRESS,
            mapOf(
                "id" to address.id,
                "zip_code" to address.zipCode,
                "country" to address.country,
                "city" to address.city,
                "street" to address.street,
                "number" to address.number,
                "state" to address.state,
                "user_id" to address.userId))

        return address
    }

    override fun update(address: Address): Address {
        namedTemplate.update(JdbcAddressRepositorySQL.UPDATE_ADDRESS,
            mapOf(
                "zip_code" to address.zipCode,
                "country" to address.country,
                "city" to address.city,
                "street" to address.street,
                "number" to address.number,
                "state" to address.state,
                "user_id" to address.userId))

        return address
    }
}