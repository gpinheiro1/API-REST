package com.movilepay.business.repository

import com.movilepay.business.model.Company


interface CompanyRepository {
    fun getAllCompanies(page: Int, size: Int): Collection<Company>
    fun getCompanyById(companyId: String): Company?
    fun createCompany(company: Company): Company
    fun updateCompany(company: Company): Company
    fun deleteCompany(companyId: String)
}