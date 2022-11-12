package com.s4ng.jobbylanguage.model.entity

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "job_opening")
@AllArgsConstructor
@NoArgsConstructor
class JobOpeningEntity (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(nullable = false)
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "stack_id")
        var stack: StackEntity,

        val value: Int,

        val createdTime: ZonedDateTime,
)