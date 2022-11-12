package com.s4ng.jobbylanguage.controller

import com.s4ng.jobbylanguage.model.dto.StatisticsDataDto
import com.s4ng.jobbylanguage.service.MainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(@Autowired val mainService: MainService) {

    @GetMapping("/")
    fun getIndex(): String {
        return "index"
    }

    @GetMapping("/data")
    fun getData(): ResponseEntity<StatisticsDataDto> {
        return ResponseEntity.ok(mainService.getData())
    }
}