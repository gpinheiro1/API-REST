package com.movilepay.business.repository

import com.movilepay.business.model.User

interface UserStoreRepository {
    fun create(user:User)
    fun update(user: User)
    fun delete(userId: String)
}