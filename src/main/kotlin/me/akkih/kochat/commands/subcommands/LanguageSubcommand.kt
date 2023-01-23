package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum.LANGUAGE
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage.*
import me.akkih.kochat.enums.Language
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import org.bukkit.command.CommandSender

class LanguageSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.size != 2) {
            sender.sendConfigMessage(INVALID_USAGE, "%usage%" to LANGUAGE.commandUsage)
            return
        }
        if (!Language.exists(args[1].uppercase())) {
            sender.sendConfigMessage(LANGUAGE_NOT_FOUND)
            return
        }

        main.config.set("language", args[1].lowercase())
        ConfigManager.reloadAll()

        sender.sendConfigMessage(LANGUAGE_CHANGED_SUCCESSFULLY)
    }

}