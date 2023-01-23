package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.sendConfigMessage
import org.bukkit.command.CommandSender

class ReloadSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        ConfigManager.reloadAll()
        sender.sendConfigMessage(ConfigMessage.SUCCESSFUL_RELOAD)
    }

}