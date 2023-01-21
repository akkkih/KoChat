package me.akkih.kochat.utils

import org.bukkit.ChatColor

object ChatUtil {

    fun format(message: String): String {
        return ChatColor.translateAlternateColorCodes('&', message)
    }

}