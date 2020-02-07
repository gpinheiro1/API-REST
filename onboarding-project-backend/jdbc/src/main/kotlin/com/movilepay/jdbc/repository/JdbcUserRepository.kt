package com.movilepay.jdbc.repository

import com.movilepay.business.model.User
import com.movilepay.business.repository.UserRepository
import com.movilepay.jdbc.repository.sql.JdbcUserRepositorySQL
import com.movilepay.jdbc.repository.sql.JdbcUserRepositorySQL.GET_USER_DETAIL
import com.movilepay.jdbc.repository.sql.JdbcUserRepositorySQL.INSERT_USER
import com.movilepay.jdbc.repository.sql.JdbcUserRepositorySQL.UPDATE_USER
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.time.ZoneId

@Repository
open class JdbcUserRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val userRowMapper: RowMapper<User>
) : UserRepository {

    private val namedTemplate by lazy {
        NamedParameterJdbcTemplate(jdbcTemplate)
    }

    override fun getById(userId: String): User? {
        return namedTemplate.query(
            GET_USER_DETAIL,
            mapOf(
                "id" to userId
            ),
            userRowMapper
        )
            .firstOrNull()
    }

    override fun create(user: User): User {
        namedTemplate.update(
            INSERT_USER,
            mapOf(
                "id" to user.id,
                "first_name" to user.firstName,
                "last_name" to user.lastName,
                "company" to user.company,
                "email" to user.email,
                "birth_date" to Timestamp.from(user.birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "type" to user.type.toString(),
                "document_id" to user.documentId
            )
        )

        return user
    }

    override fun update(user: User): User {
        namedTemplate.update(
            UPDATE_USER,
            mapOf(
                "id" to user.id,
                "first_name" to user.firstName,
                "last_name" to user.lastName,
                "company" to user.company,
                "email" to user.email,
                "birth_date" to Timestamp.from(user.birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                "type" to user.type.toString(),
                "document_id" to user.documentId
            )
        )

        return user
    }

    override fun delete(userId: String) {
        namedTemplate.update(
            JdbcUserRepositorySQL.DELETE_USER,
            mapOf(
                "id" to userId
            )
        )
    }

    override fun getAll(
        page: Int,
        size: Int
    ): Collection<User> {
        return namedTemplate.query(
            JdbcUserRepositorySQL.GET_USERS,
            mapOf(
                "page" to page,
                "size" to size
            ),
            userRowMapper
        )
    }
}