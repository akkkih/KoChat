package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class WordSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.size != 3 && args[1].lowercase() != "list") {
            sender.sendConfigMessage(ConfigMessage.INVALID_USAGE, "%usage%" to SubcommandEnum.WORDS.commandUsage)
            return
        }

        val swearList = main.config.getStringList("swears")

        when (args[1].lowercase()) {
            "add" -> {
                if (swearList.contains(args[2])) {
                    sender.sendConfigMessage(ConfigMessage.WORD_ALREADY_ADDED, Pair("%word%", args[2]))
                    return
                }

                swearList.add(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()
                sender.sendConfigMessage(ConfigMessage.ADDED_WORD, Pair("%word%", args[2]))
            }
            "remove" -> {
                if (!swearList.contains(args[2])) {
                    sender.sendConfigMessage(ConfigMessage.WORD_NOT_FOUND, Pair("%word%", args[2]))
                    return
                }

                swearList.remove(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()

                sender.sendConfigMessage(ConfigMessage.REMOVED_WORD, Pair("%word%", args[2]))
            }
            "list" -> {
                sender.sendMessage(ChatUtil.format("&8----------------------- &7[&6&lKC&7]&8 -----------------------"))
                for (swear in swearList) {
                    sender.sendMessage(ChatUtil.format("&8- &6${swear}"))
                }
                if (swearList.isEmpty()) sender.sendConfigMessage(ConfigMessage.NO_WORDS)
                sender.sendMessage(ChatUtil.format("&8---------------------------------------------------"))
            }
            else -> {
                sender.sendConfigMessage(ConfigMessage.INVALID_USAGE, "%usage%" to SubcommandEnum.WORDS.commandUsage)
            }
        }
    }

}