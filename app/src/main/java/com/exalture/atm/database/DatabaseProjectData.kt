package com.exalture.atm.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exalture.atm.domain.ExaltureProjects

@Entity(tableName = "project_table")
data class DatabaseProjectData constructor(
    @PrimaryKey
    val id: String,
    val logoImgSrcUrl: String,
    val detailsImgSrcUrl: String,
    val type: String,
    val title: String,
    val description: String
)

fun List<DatabaseProjectData>.asDomainModel(): List<ExaltureProjects> {
    return map {
        ExaltureProjects(
            id = it.id,
            type = it.type,
            title = it.title,
            description = it.description,
            logoImgSrcUrl = it.logoImgSrcUrl,
            detailsImgSrcUrl = it.detailsImgSrcUrl
        )
    }
}