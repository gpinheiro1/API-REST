package com.movilepay.webservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import java.time.Instant
import java.time.LocalDate

@SpringBootApplication
@ComponentScan("com.movilepay")
open class WebServiceApplication

fun main(args: Array<String>) {
    runApplication<WebServiceApplication>(*args)
}
