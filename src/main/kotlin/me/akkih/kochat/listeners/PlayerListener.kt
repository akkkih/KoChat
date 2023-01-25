package me.akkih.kochat.listeners

import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        event.joinMessage = ChatUtil.format(
            ConfigManager.getMessage(ConfigMessage.JOIN_MESSAGE)
                .replace("%player%", event.player.name))
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.quitMessage = ChatUtil.format(
            ConfigManager.getMessage(ConfigMessage.QUIT_MESSAGE)
                .replace("%player%", event.player.name))
    }

}