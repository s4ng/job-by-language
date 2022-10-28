package com.s4ng.jobbylanguage.model.entity

import com.s4ng.jobbylanguage.model.enum.JobCategory
import com.s4ng.jobbylanguage.model.enum.StackCategory
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Table(name = "stack")
@AllArgsConstructor
@NoArgsConstructor
class StackEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        var name: String? = null,

        @Enumerated(EnumType.STRING)
        var stackCategory: StackCategory? = null,

        @Enumerated(EnumType.STRING)
        var jobCategory: JobCategory? = null
) {
    @OneToMany(mappedBy = "stack")
    var _jobOpening: MutableList<JobOpeningEntity> = mutableListOf();
    val jobOpenings: List<JobOpeningEntity>
        get() = _jobOpening.toList();

    fun join(jobOpening: JobOpeningEntity) {
        _jobOpening += jobOpening
    }
}