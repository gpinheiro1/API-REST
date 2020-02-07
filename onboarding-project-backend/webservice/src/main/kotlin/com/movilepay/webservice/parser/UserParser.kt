package com.movilepay.webservice.parser

import com.movilepay.api.address.AddressResponse
import com.movilepay.api.user.UserDetailResponse
import com.movilepay.api.user.UserRequest
import com.movilepay.api.user.UserResponse
import com.movilepay.business.model.Address
import com.movilepay.business.model.User
import java.util.UUID

object UserParser {

    fun parseToUserResponse(userModel: User): UserResponse {
        return UserResponse(
            firstName = userModel.firstName,
            lastName = userModel.lastName,
            documentId = userModel.documentId,
            type = userModel.type,
            company = userModel.company,
            birthDate = userModel.birthDate,
            email = userModel.email,
            id = userModel.id
        )
    }

    fun parseToUserDetailResponse(userModel: User): UserDetailResponse {
        return UserDetailResponse (
            firstName = userModel.firstName,
            lastName = userModel.lastName,
            documentId = userModel.documentId,
            type = userModel.type,
            company = userModel.company,
            birthDate = userModel.birthDate,
            email = userModel.email,
            id = userModel.id,
            address = AddressResponse(
                id = userModel.address.id,
                zipCode = userModel.address.zipCode,
                street = userModel.address.street,
                state = userModel.address.state,
                number = userModel.address.number,
                country = userModel.address.country,
                city = userModel.address.city,
                userId = userModel.address.userId
            )
        )
    }

    fun parseToUserModel(userRequest: UserRequest) : User {
        return parseToUserModel(userRequest, UUID.randomUUID().toString())
    }

    fun parseToUserModel(userRequest: UserRequest, userId: String) : User {
        return User(
            firstName = userRequest.firstName,
            lastName = userRequest.lastName,
            documentId = userRequest.documentId,
            type = userRequest.type,
            company = userRequest.company,
            birthDate = userRequest.birthDate,
            email = userRequest.email,
            id = userId,
            address = Address(
                zipCode = userRequest.address.zipCode,
                street = userRequest.address.street,
                state = userRequest.address.state,
                number = userRequest.address.number,
                country = userRequest.address.country,
                city = userRequest.address.city,
                userId = userId,
                id = UUID.randomUUID().toString()
            )
        )
    }

}