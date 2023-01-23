package me.akkih.kochat.commands.subcommands

import me.akkih.kochat.commands.utils.SubcommandEnum
import me.akkih.kochat.commands.utils.SubcommandManager
import me.akkih.kochat.utils.ChatUtil
import org.bukkit.command.CommandSender

class HelpSubcommand : SubcommandManager() {

    override fun execute(sender: CommandSender, args: Array<String>) {
        sender.sendMessage(ChatUtil.format("&8----------------------- &7[&6&lKC&7]&8 -----------------------"))
        for (subEnum in SubcommandEnum.values()) {
            sender.sendMessage(ChatUtil.format("&8- &6${subEnum.command}&8: &7${subEnum.commandUsage}"))
        }
        sender.sendMessage(ChatUtil.format("&8---------------------------------------------------"))
    }

}