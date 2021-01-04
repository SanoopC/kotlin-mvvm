package com.exalture.atm.network

import com.exalture.atm.database.DatabaseProjectData
import com.squareup.moshi.Json

data class NetworkExaltureProjects(
    val id: String,
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "logo_img_src") val logoImgSrcUrl: String,
    @Json(name = "details_img_src") val detailsImgSrcUrl: String,
    val type: String,
    val title: String,
    val description: String
)

fun List<NetworkExaltureProjects>.asDatabaseModel(): List<DatabaseProjectData> {
    return map {
        DatabaseProjectData(
            id = it.id,
            logoImgSrcUrl = it.logoImgSrcUrl,
            detailsImgSrcUrl = it.detailsImgSrcUrl,
            type = it.type,
            title = it.title,
            description = it.description
        )
    }
}