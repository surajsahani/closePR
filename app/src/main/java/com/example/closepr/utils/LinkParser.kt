package com.example.closepr.utils

import java.util.regex.Pattern

object LinkParser {

    fun parseNext(link: String?): String? {
        return parseLInk(link, "next")
    }

    fun parsePrev(link: String?): String? {
        return parseLInk(link, "prev")
    }

    private fun parseLInk(link: String?, label: String): String? {
        link?.apply {
            val links = split(",")
            val prev = links.filter {
                it.contains("rel=\"$label\"")
            }
            prev.getOrNull(0)?.apply {
                val pattern = Pattern.compile("<(.*?)>")
                val matcher = pattern.matcher(this)
                return if (matcher.find()) matcher.group(1) else null
            }

            return null
        }
        return null
    }
}