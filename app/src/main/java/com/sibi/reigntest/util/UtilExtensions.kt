package com.sibi.reigntest.util

import com.sibi.reigntest.data.local.PostDatabaseEntity
import com.sibi.reigntest.data.remote.PostRemoteDTO
import com.sibi.reigntest.data.repository.PostDomainModel

import java.text.SimpleDateFormat
import java.util.*


fun String.toDate(
    dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    return parser.parse(this)
}

fun Date.formatTo(dateFormat: String, timeZone: TimeZone = TimeZone.getDefault()): String {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(this)
}

fun PostDatabaseEntity.asDomainModel(): PostDomainModel {
    return PostDomainModel(
        id = this.story_id,
        title = PostUtil.firsSecondOrNull(this.story_title, this.title),
        author = author,
        createdAt = PostUtil.dateDiff(this.created_at.toDate()),
        url = PostUtil.firsSecondOrNull(this.story_url, this.url)
    )
}

fun PostRemoteDTO.asDatabaseEntity(): PostDatabaseEntity {
    return PostDatabaseEntity(
        story_id = this.objectID,
        story_title = this.story_title,
        title = this.title,
        author = this.author,
        created_at = this.created_at,
        story_url = this.story_url,
        url = this.url
    )
}