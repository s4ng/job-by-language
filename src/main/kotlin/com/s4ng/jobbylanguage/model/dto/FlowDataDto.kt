package com.s4ng.jobbylanguage.model.dto

import java.time.ZonedDateTime

data class FlowDataDto(val names: List<String>, val values: List<Int>, val time: List<ZonedDateTime>)