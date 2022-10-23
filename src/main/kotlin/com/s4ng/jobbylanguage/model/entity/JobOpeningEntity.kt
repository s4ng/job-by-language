package com.s4ng.jobbylanguage.model.entity

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "job_opening")
class JobOpeningEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "language_id")
        val language: LanguageEntity,

        val openings: Int,

        val createdTime: ZonedDateTime
) {

}