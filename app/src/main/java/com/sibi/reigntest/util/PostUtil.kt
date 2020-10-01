package com.sibi.reigntest.util

import java.util.*

object PostUtil {

    fun dateDiff(date: Date): String {
        val now = Date()
        val diff: Long = now.time - date.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            minutes < 60 -> {
                "" + minutes + "m"
            }
            hours < 24 -> {
                "" + hours + "h"
            }
            hours < 48 -> {
                "Yesterday"
            }
            days < 8 -> {
                "" + days + "days"
            }
            else -> {
                date.formatTo("dd MMM yyyy")
            }
        }
    }

    fun firsSecondOrNull(first: String?, second: String?): String? {
        return when {
            first?.isNotEmpty() == true -> {
                first
            }
            second?.isNotEmpty() == true -> {
                second
            }
            else -> null
        }
    }

}
