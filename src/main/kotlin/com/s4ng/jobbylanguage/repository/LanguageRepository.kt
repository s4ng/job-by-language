package com.s4ng.jobbylanguage.repository

import com.s4ng.jobbylanguage.model.entity.LanguageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LanguageRepository : JpaRepository<LanguageEntity, Long> {
}