package me.akkih.kochat

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import me.akkih.kochat.commands.BaseCommand
import me.akkih.kochat.utils.JsonUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

internal var isPluginUpdated = true
lateinit var main: KoChat

class KoChat : JavaPlugin() {

    override fun onEnable() {
        main = this

        if (!dataFolder.exists()) dataFolder.mkdir()
        saveDefaultConfig()

        saveResource("messages${File.separator}en_us.yml", false)
        saveResource("messages${File.separator}pt_br.yml", false)

        BaseCommand()
        checkIsPluginUpdated()
    }

    private fun checkIsPluginUpdated() {
        Bukkit.getScheduler().runTaskAsynchronously(this, Runnable {
            val mapper = ObjectMapper()

            val root: JsonNode = try {
                mapper.readValue(JsonUtil.getDataJSON(), JsonNode::class.java)
            } catch (error: JsonProcessingException) {
                throw RuntimeException(error)
            }

            if (root.get("latestVersion").toString() != description.version) {
                logger.warning("You are running an outdated version of KoChat, please update to the latest version!")
                isPluginUpdated = false
            }
        })
    }

}