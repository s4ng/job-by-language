package com.s4ng.jobbylanguage.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@EntityScan("com.s4ng.jobbylanguage.model.entity")
@SpringBootApplication
class JobbylanguageApplication

fun main(args: Array<String>) {
    runApplication<JobbylanguageApplication>(*args)
}
