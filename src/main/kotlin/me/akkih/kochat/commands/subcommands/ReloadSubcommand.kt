package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class ReloadSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        // TODO: Still need to add reload methods
        sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.SUCCESSFUL_RELOAD)))
    }

}