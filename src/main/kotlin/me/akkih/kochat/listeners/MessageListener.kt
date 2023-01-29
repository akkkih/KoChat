package me.akkih.kochat.listeners

import me.akkih.kochat.commands.subcommands.isChatEnabled
import me.akkih.kochat.config.TextManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class MessageListener : Listener {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        event.isCancelled = true

        if (!isChatEnabled && !event.player.hasPermission("kochat.bypass_muted_chat")) {
            event.player.sendConfigMessage(ConfigMessage.CHAT_IS_DISABLED)
            return
        }

        val messageFormat = main.config.getString("message_format")!!
        val filteredMessage = TextManager.removeSwearing(event.message)

        for (player in Bukkit.getOnlinePlayers()) {
            player.sendMessage(
                ChatUtil.format(
                    messageFormat
                        .replace("%player%", event.player.displayName)
                        .replace("%message%", filteredMessage)
                )
            )
        }
    }

}