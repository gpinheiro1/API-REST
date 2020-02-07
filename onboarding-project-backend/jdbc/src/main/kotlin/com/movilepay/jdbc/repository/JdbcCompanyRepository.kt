package com.movilepay.jdbc.repository

import com.movilepay.business.model.Company
import com.movilepay.business.repository.CompanyRepository
import com.movilepay.jdbc.mapper.CompanyRowMapper
import com.movilepay.jdbc.repository.sql.JdbcCompanyRepositorySQL.DELETE_COMPANY
import com.movilepay.jdbc.repository.sql.JdbcCompanyRepositorySQL.GET_ALL_COMPANIES
import com.movilepay.jdbc.repository.sql.JdbcCompanyRepositorySQL.GET_COMPANY_BY_ID
import com.movilepay.jdbc.repository.sql.JdbcCompanyRepositorySQL.INSERT_COMPANY
import com.movilepay.jdbc.repository.sql.JdbcCompanyRepositorySQL.UPDATE_COMPANY
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.security.PrivateKey

@Repository
class JdbcCompanyRepository(
    private val jdbcTemplate: JdbcTemplate,
    private val companyRowMapper: RowMapper<Company>
) : CompanyRepository {

    override fun getAllCompanies(page: Int, size: Int): Collection<Company> {
        return namedTemplate.query(
            GET_ALL_COMPANIES,
            mapOf(
                "page" to page,
                "size" to size
            ),
            companyRowMapper
        )
    }

    private val namedTemplate by lazy {
        NamedParameterJdbcTemplate(jdbcTemplate)
    }


    override fun deleteCompany(companyId: String) {
        namedTemplate.update(
            DELETE_COMPANY,
            mapOf(
                "id" to companyId
            )
        )
    }

    override fun getCompanyById(companyId: String): Company? {
        return namedTemplate.query(
            GET_COMPANY_BY_ID,
            mapOf(
                "id" to companyId
            ),
            companyRowMapper
        )
            .firstOrNull()
    }

    override fun createCompany(company: Company): Company {
        namedTemplate.update(
            INSERT_COMPANY,
            mapOf(
                "id" to company.companyId,
                "name" to company.companyName,
                "email" to company.email
            )
        )
        return company
    }

    override fun updateCompany(company: Company): Company {
        namedTemplate.update(
            UPDATE_COMPANY,
            mapOf(
                "id" to company.companyId,
                "name" to company.companyName,
                "email" to company.email
            )
        )
        return company
    }

}