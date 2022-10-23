package com.s4ng.jobbylanguage.model.entity

import com.s4ng.jobbylanguage.model.enum.JobCategory
import com.s4ng.jobbylanguage.model.enum.LanguageCategory
import javax.persistence.*

@Entity
@Table(name = "language")
class LanguageEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var name: String,

        @Enumerated(EnumType.STRING)
        var languageCategory: LanguageCategory,

        @Enumerated(EnumType.STRING)
        var jobCategory: JobCategory
) {

        @OneToMany(mappedBy = "language")
        private var _jobOpening: MutableList<JobOpeningEntity> = mutableListOf();
        val jobOpenings: List<JobOpeningEntity>
                get() = _jobOpening.toList();

        fun join(jobOpening: JobOpeningEntity) {
                _jobOpening += jobOpening
        }
}