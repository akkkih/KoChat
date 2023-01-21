package me.akkih.kochat.commands.utils

import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import org.bukkit.command.CommandSender

abstract class SubcommandManager {

    fun execute(sender: CommandSender, args: Array<String>, subcommandEnum: SubcommandEnum): Boolean {
        return if (sender.hasPermission(subcommandEnum.permission)) {
            execute(sender, args)
            true
        } else {
            sender.sendMessage(ConfigManager.getMessage(ConfigMessage.NO_PERMISSION_MESSAGE))
            false
        }
    }

    abstract fun execute(sender: CommandSender, args: Array<String>)

}