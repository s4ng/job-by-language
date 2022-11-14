package com.s4ng.jobbylanguage.model.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class StatisticsDataDto(
        val languageRank: RankDataDto,
        val languageFlow: FlowDataDto?,

        val frameworkRank: RankDataDto,
        val frameworkFlow: FlowDataDto?,

        val databaseRank: RankDataDto,
        val databaseFlow: FlowDataDto?,

        val lowLanguageRank: RankDataDto?,
        val lowFrameworkRank: RankDataDto?,
)