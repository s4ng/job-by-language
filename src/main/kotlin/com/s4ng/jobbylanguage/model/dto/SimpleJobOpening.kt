package com.s4ng.jobbylanguage.model.dto

import java.time.ZonedDateTime

data class SimpleJobOpening(val name: String,
                            var value: Int,
                            var count: Int,
                            val time: ZonedDateTime) {
    fun addValue(value: Int) {
        this.value += value
        this.count++
    }
}