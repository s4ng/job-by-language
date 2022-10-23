package com.s4ng.jobbylanguage.model.entity

import com.s4ng.jobbylanguage.model.enum.LanguageCategory
import javax.persistence.*

@Entity
@Table(name = "language")
class LanguageEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var name: String,

        var category: LanguageCategory
) {

}