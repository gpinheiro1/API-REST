package com.movilepay.webservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Configuration
open class DatabaseConfig(
    @Value("\${spring.datasource.url}") private val databaseUrl: String,
    @Value("\${spring.datasource.username}") private val databaseUser: String,
    @Value("\${spring.datasource.password}") private val databasePassword: String,
    @Value("\${spring.datasource.driverClassName}") private val databaseDriver: String
) {

    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(databaseDriver)
        dataSource.url = databaseUrl
        dataSource.username = databaseUser
        dataSource.password = databasePassword
        return dataSource
    }
}