package com.movilepay.webservice.config

import com.movilepay.business.repository.UserStoreRepository
import com.movilepay.third.party.ldap.repository.LdapUserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.core.support.LdapContextSource
import javax.naming.ldap.LdapName


@Configuration
class LdapConfig(
    @Value("\${spring.ldap.base}") private val ldapBase: String,
    @Value("\${spring.ldap.username}") private val ldapUser: String,
    @Value("\${spring.ldap.urls}") private val ldapUrl: String,
    @Value("\${spring.ldap.password}") private val ldapPassword: String) {

    @Bean
    fun contextSource(): LdapContextSource {
        val contextSource = LdapContextSource()
        contextSource.setUrl(ldapUrl)
        contextSource.userDn = ldapUser
        contextSource.password = ldapPassword
        return contextSource
    }

    @Bean
    fun ldapTemplate(): LdapTemplate {
        return LdapTemplate(contextSource())
    }

    @Bean
    fun ldapName(): LdapName{
        return LdapName(ldapBase)
    }

    @Bean
    fun userStoreRepository() : UserStoreRepository {
        return LdapUserRepository(ldapTemplate())
    }
}