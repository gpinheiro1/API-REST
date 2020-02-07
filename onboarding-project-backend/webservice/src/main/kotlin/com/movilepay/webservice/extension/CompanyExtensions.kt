package com.movilepay.webservice.extension

import com.movilepay.api.company.CompanyDetailResponse
import com.movilepay.api.company.CompanyRequest
import com.movilepay.api.company.CompanyResponse
import com.movilepay.business.model.Company
import java.util.*

fun Company.toCompanyDetailResponse() =
    CompanyDetailResponse(
        id = this.companyId,
        name = this.companyName,
        email = this.email
    )

fun CompanyRequest.toCompany() =
    Company(
        companyId = UUID.randomUUID().toString(),
        companyName = this.name,
        email = this.email
    )

fun Company.toCompanyResponse() =
    CompanyResponse(
        id = this.companyId,
        name = this.companyName,
        email = this.email
    )

