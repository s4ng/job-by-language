package com.s4ng.jobbylanguage.repository

import com.s4ng.jobbylanguage.model.entity.StackEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StackRepository : JpaRepository<StackEntity, Long> {

    fun findByNameIgnoreCase(name: String) : StackEntity?
}