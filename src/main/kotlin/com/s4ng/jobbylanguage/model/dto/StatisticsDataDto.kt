package com.s4ng.jobbylanguage.model.dto

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