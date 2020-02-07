package com.movilepay.webservice.controller

import com.movilepay.api.company.CompanyDetailResponse
import com.movilepay.api.company.CompanyRequest
import com.movilepay.api.company.CompanyResponse
import com.movilepay.business.model.Company
import com.movilepay.business.service.CompanyService
import com.movilepay.webservice.extension.toCompany
import com.movilepay.webservice.extension.toCompanyDetailResponse
import com.movilepay.webservice.extension.toCompanyResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping(CompanyController.ROUTE, produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class CompanyController(
    private val companyService: CompanyService
) {

    companion object {
        const val ROUTE: String = "/companies"
    }

    @GetMapping
    fun getCompanies(
        @RequestParam("page", required = false, defaultValue = "0") page: Int,
        @RequestParam("count", required = false, defaultValue = "5") count: Int
    ): ResponseEntity<Collection<CompanyResponse>> {
        val companies = companyService.getAll(page, count)
        return ResponseEntity.ok(companies.map { it.toCompanyResponse() })
    }

    @GetMapping("/{companyId}")
    fun getCompanyById(
        @PathVariable("companyId") companyId: String
    ): ResponseEntity<CompanyDetailResponse> =
        try {
            val company: Company = companyService.getCompanyById(companyId)
            ResponseEntity.ok(company.toCompanyDetailResponse())
        } catch (e: IllegalArgumentException) {
            ResponseEntity.notFound().build()
        }

    @PostMapping
    fun createCompany(
        @RequestBody
        companyRequest: CompanyRequest
    ): ResponseEntity<CompanyDetailResponse> {
        val company: Company = companyService.createCompany(companyRequest.toCompany())
        val response = company.toCompanyDetailResponse()
        return ResponseEntity(response, HttpStatus.CREATED) //isso está muito verboso? existe o metodo created() do responseEntity. Li sobre HATEOAS
    }

    @PutMapping
    fun updateCompany(
        @RequestBody
        companyRequest: CompanyRequest
    ): ResponseEntity<CompanyDetailResponse> {
        val company: Company = companyService.updateCompany(companyRequest.toCompany())
        return ResponseEntity.ok(company.toCompanyDetailResponse())
    }
}