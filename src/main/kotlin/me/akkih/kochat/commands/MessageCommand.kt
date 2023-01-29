package me.akkih.kochat.commands

import me.akkih.kochat.commands.utils.CommandManager
import me.akkih.kochat.config.enums.ConfigMessage.*
import me.akkih.kochat.main
import me.akkih.kochat.sendConfigMessage
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.util.StringUtil
import java.util.*
import kotlin.collections.ArrayList

class MessageCommand : CommandManager(
    "message",
    arrayOf("msg", "tell", "whisper", "w"),
    "Message other players",
    null
) {

    override fun execute(sender: CommandSender, args: Array<String>) {
        if (sender !is Player) {
            sender.sendConfigMessage(NO_CONSOLE)
            return
        }
        if (args.size < 2) {
            sender.sendConfigMessage(INVALID_USAGE, Pair("%usage%", "/tell <player> <message>"))
            return
        }
        if (Bukkit.getPlayerExact(args[0]) == null) {
            sender.sendConfigMessage(PLAYER_NOT_ONLINE)
            return
        }

        val target = Bukkit.getPlayerExact(args[0])!!
        val message = StringBuilder()

        for (i in 1 until args.size) {
            message.append(args[i]).append(" ")
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

        // Adding target to HashMap
        main.getRecentMessages()[sender.uniqueId] = target.uniqueId
    }

    override fun onTabComplete(sender: CommandSender, args: Array<String>): MutableList<String> {
        val players = ArrayList<String>()
        for (player in Bukkit.getOnlinePlayers()) players.add(player.name)

        if (args.size == 1) {
            return StringUtil.copyPartialMatches(
                args[0],
                players,
                ArrayList()
            )
        }

        return mutableListOf()
    }

}