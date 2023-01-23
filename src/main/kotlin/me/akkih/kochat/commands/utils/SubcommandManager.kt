package me.akkih.kochat.commands.utils

import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.sendConfigMessage
import org.bukkit.command.CommandSender

abstract class SubcommandManager {

    fun execute(sender: CommandSender, args: Array<String>, subcommandEnum: SubcommandEnum): Boolean {
        return if (sender.hasPermission(subcommandEnum.permission)) {
            execute(sender, args)
            true
        } else {
            sender.sendConfigMessage(ConfigMessage.NO_PERMISSION_MESSAGE)
            false
        }
    }

    abstract fun execute(sender: CommandSender, args: Array<String>)

}