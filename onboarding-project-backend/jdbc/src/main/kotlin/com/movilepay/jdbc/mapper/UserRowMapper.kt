package com.movilepay.jdbc.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.movilepay.api.user.UserType
import com.movilepay.business.model.Address
import com.movilepay.business.model.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class UserRowMapper : RowMapper<User> {
    override fun mapRow(rs: ResultSet, i: Int): User? {
        return User(
            id = rs.getString("id"),
            firstName = rs.getString("first_name"),
            lastName = rs.getString("last_name"),
            email = rs.getString("email"),
            birthDate = rs.getTimestamp("birth_date").toLocalDateTime().toLocalDate(),
            company = rs.getString("company"),
            type = UserType.valueOf(rs.getString("type")),
            documentId = rs.getString("document_id"),
            address = Address(
                id = rs.getString("id"),
                city = rs.getString("city"),
                country = rs.getString("country"),
                number = rs.getInt("number"),
                state = rs.getString("state"),
                street = rs.getString("street"),
                zipCode = rs.getString("zip_code"),
                userId = rs.getString("user_id")
            )
        )
    }
}