package com.movilepay.jdbc.mapper

import com.movilepay.business.model.Company
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class CompanyRowMapper : RowMapper<Company> {
    override fun mapRow(rs: ResultSet, i: Int): Company? {
        return Company(
            companyId = rs.getString("id"),
            companyName = rs.getString("name"),
            email = rs.getString("email")
        )
    }

}