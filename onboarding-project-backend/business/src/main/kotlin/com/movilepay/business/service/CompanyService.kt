package com.movilepay.business.service

import com.movilepay.business.model.Company
import com.movilepay.business.repository.CompanyRepository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun getAll(page: Int, size: Int): Collection<Company> =
        companyRepository.getAllCompanies(page, size)

    fun getCompanyById(companyId: String): Company =
        companyRepository.getCompanyById(companyId) ?: throw IllegalArgumentException("Company not Found")

    fun createCompany(company: Company): Company =
        companyRepository.createCompany(company)

    fun updateCompany(company: Company): Company =
        companyRepository.updateCompany(company)
}