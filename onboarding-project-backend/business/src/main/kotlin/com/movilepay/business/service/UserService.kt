package com.movilepay.business.service

import com.movilepay.business.model.User
import com.movilepay.business.repository.UserRepository
import com.movilepay.business.repository.UserStoreRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.time.Instant
import java.time.LocalDate

@Service
class UserService(
    private val userRepository: UserRepository,
    private val addressService: AddressService,
    private val userStoreRepository: UserStoreRepository
) {
    fun getAllUsers(
        page: Int,
        size: Int
    ): Collection<User> = userRepository.getAll(page, size)

    @Transactional
    fun create(user: User): User {

        validateUserRequest(user)
        userStoreRepository.create(user)
        val createdUser = userRepository.create(user)
        addressService.create(user.address, user.id)

        return createdUser
    }

    fun getUserById(userId: String): User? {
        return userRepository.getById(userId) ?: throw
        IllegalArgumentException("User not found.")
    }

    @Transactional
    fun deleteUser(userId: String) {
        userStoreRepository.delete(userId)
        addressService.deleteByUserId(userId)
        userRepository.delete(userId)
    }

    @Transactional
    fun updateUser(userModel: User): User {
        validateUserRequest(userModel)
        userStoreRepository.update(userModel)
        addressService.updateAddress(userModel.address)
        return userRepository.update(userModel)
    }

    private fun validateUserRequest(user: User) {
        require(
            user.email.isNotEmpty() &&
                    user.lastName.isNotEmpty() &&
                    user.firstName.isNotEmpty() &&
                    user.company.isNotEmpty() &&
                    user.documentId.isNotEmpty() &&
                    user.type.toString().isNotEmpty() &&
                    user.birthDate.isBefore(LocalDate.now())
        ) { "Request for user creation is invalid." }
    }
}