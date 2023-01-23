package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.config.ConfigManager
import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.main
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class WordSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (args.size > 3) {
            sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.INVALID_USAGE)
                .replace("%usage%", SubcommandEnum.WORDS.commandUsage)))
            return
        }

        val swearList = main.config.getStringList("swears")

        when (args[1].lowercase()) {
            "add" -> {
                if (swearList.contains(args[2])) {
                    sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.WORD_ALREADY_ADDED)
                        .replace("%word%", args[2])))
                    return
                }

                swearList.add(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()

                sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.ADDED_WORD)
                    .replace("%word%", args[2])))
            }
            "remove" -> {
                if (!swearList.contains(args[2])) {
                    sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.WORD_NOT_FOUND)
                        .replace("%word%", args[2])))
                    return
                }

                swearList.remove(args[2])
                main.config.set("swears", swearList)

                ConfigManager.reloadAll()

                sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.REMOVED_WORD)
                    .replace("%word%", args[2])))
            }
            "list" -> {
                sender.sendMessage(ChatUtil.format("&8----------------------- &7[&6&lKC&7]&8 -----------------------"))
                for (swear in swearList) {
                    sender.sendMessage(ChatUtil.format("&8- &6${swear}"))
                }
                if (swearList.isEmpty()) sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.NO_WORDS)))
                sender.sendMessage(ChatUtil.format("&8---------------------------------------------------"))
            }
            else -> {
                sender.sendMessage(ChatUtil.format(ConfigManager.getMessage(ConfigMessage.INVALID_USAGE)
                    .replace("%usage%", SubcommandEnum.WORDS.commandUsage)))
            }
        }
    }

}