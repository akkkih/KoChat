package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.enums.ConfigMessage.*
import me.akkih.kochat.sendConfigMessage
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

var isChatEnabled = true

class ToggleChatSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        isChatEnabled = !isChatEnabled

        for (player in Bukkit.getOnlinePlayers()) {
            if (!isChatEnabled) {
                player.sendConfigMessage(CHAT_WAS_DISABLED, Pair("%player%", sender.name))
            } else {
                player.sendConfigMessage(CHAT_WAS_ENABLED, Pair("%player%", sender.name))
            }
        }
    }

}