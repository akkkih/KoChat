package me.akkih.kochat.commands

import me.akkih.kochat.commands.utils.CommandManager
import me.akkih.kochat.config.enums.ConfigMessage.*
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ReplyCommand : CommandManager(
    "reply",
    arrayOf("r", "re"),
    "Replies to the last player you messaged",
    null
) {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (sender !is Player) {
            sender.sendConfigMessage(NO_CONSOLE)
            return
        }
        if (args.isEmpty()) {
            sender.sendConfigMessage(INVALID_USAGE, Pair("%usage%", "/reply <message>"))
            return
        }
        if (!main.getRecentMessages().containsKey(sender.uniqueId)) {
            sender.sendConfigMessage(DID_NOT_MESSAGE_ANYONE)
            return
        }

        val targetUUID = main.getRecentMessages()[sender.uniqueId]!!

        if (Bukkit.getPlayer(targetUUID) == null) {
            sender.sendConfigMessage(PLAYER_NOT_ONLINE)
            return
        }

        val target = Bukkit.getPlayer(targetUUID)!!
        val message = StringBuilder()

        for (element in args) {
            message.append(element).append(" ")
        }

        sender.sendConfigMessage(
            SENT_MESSAGE,
            Pair("%player%", target.displayName),
            Pair("%message%", message.toString())
        )

        target.sendConfigMessage(
            RECEIVED_MESSAGE,
            Pair("%player%", sender.displayName),
            Pair("%message%", message.toString())
        )
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String> {
        return mutableListOf()
    }

}