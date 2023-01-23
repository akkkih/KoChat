package me.akkih.kochat.config

import me.akkih.kochat.main

object TextManager {

    fun removeSwearing(string: String): String {
        for (swear in main.config.getStringList("swears")) {
            val swearIgnoreCase = swear.toRegex(RegexOption.IGNORE_CASE)

            if (string.contains(swearIgnoreCase)) {
                return string.replace(swearIgnoreCase, "*".repeat(swear.length))
            }
        }

        return string
    }

}