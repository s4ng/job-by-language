package com.s4ng.jobbylanguage.bootstrap

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EntityScan("com.s4ng.jobbylanguage.model.entity")
@EnableJpaRepositories("com.s4ng.jobbylanguage.repository")
@EnableScheduling
@SpringBootApplication(scanBasePackages = ["com.s4ng.jobbylanguage"])
class JobbylanguageApplication

fun main(args: Array<String>) {
    runApplication<JobbylanguageApplication>(*args)
}
