package com.bruno.customerprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomerProcessorApplication

fun main(args: Array<String>) {
    runApplication<CustomerProcessorApplication>(*args)
}
