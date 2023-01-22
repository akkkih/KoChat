package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.config.enums.Language
import me.akkih.kochat.main
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class LanguageSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.size != 2) {
            sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.INVALID_USAGE)
                .replace("%usage%", SubcommandEnum.LANGUAGE.commandUsage)))
            return
        }
        if (!Language.exists(args[1].uppercase())) {
            sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.LANGUAGE_NOT_FOUND)))
            return
        }

        main.config.set("language", args[1].lowercase())
        ConfigManager.reloadAll()

        sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.LANGUAGE_CHANGED_SUCCESSFULLY)))
    }

}