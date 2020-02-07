package com.movilepay.business.repository

import com.movilepay.business.model.User

interface UserRepository {
    fun getAll(page: Int,
               size: Int): Collection<User>
    fun getById(userId: String): User?
    fun create(user: User): User
    fun update(user: User): User
    fun delete(userId: String)
}