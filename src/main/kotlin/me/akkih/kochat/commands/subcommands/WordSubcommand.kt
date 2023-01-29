package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum.WORDS
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage.*
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class WordSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.size < 2) {
            sender.sendConfigMessage(INVALID_USAGE, "%usage%" to WORDS.commandUsage)
            return
        }

        val swearList = main.config.getStringList("swears")

        when (args[1].lowercase()) {
            "add" -> {
                if (args.size != 3) {
                    sender.sendConfigMessage(INVALID_USAGE, "%usage%" to WORDS.commandUsage)
                    return
                }
                if (swearList.contains(args[2])) {
                    sender.sendConfigMessage(WORD_ALREADY_ADDED, "%word%" to args[2])
                    return
                }

                swearList.add(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()
                sender.sendConfigMessage(ADDED_WORD, "%word%" to args[2])
            }

            "remove" -> {
                if (args.size != 3) {
                    sender.sendConfigMessage(INVALID_USAGE, "%usage%" to WORDS.commandUsage)
                    return
                }
                if (!swearList.contains(args[2])) {
                    sender.sendConfigMessage(WORD_NOT_FOUND, "%word%" to args[2])
                    return
                }

                swearList.remove(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()

                sender.sendConfigMessage(REMOVED_WORD, "%word%" to args[2])
            }

            "list" -> {
                sender.sendMessage(ChatUtil.format("&8----------------------- &7[&6&lKC&7]&8 -----------------------"))
                sender.sendConfigMessage(LIST_OF_WORDS)

                for (swear in swearList) {
                    sender.sendMessage(ChatUtil.format("&8- &6${swear}"))
                }

                if (swearList.isEmpty()) sender.sendConfigMessage(NO_WORDS)
                sender.sendMessage(ChatUtil.format("&8---------------------------------------------------"))
            }

            else -> {
                sender.sendConfigMessage(INVALID_USAGE, "%usage%" to WORDS.commandUsage)
            }
        }
    }

}