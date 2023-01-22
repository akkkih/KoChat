package me.akkih.kochat.config

import me.akkih.kochat.config.enums.ConfigMessage
import me.akkih.kochat.main
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object ConfigManager {
    private lateinit var yamlConfiguration: YamlConfiguration
    private lateinit var path: String
    private lateinit var file: File
    init {
        loadMessages()
    }

    fun getMessage(path: ConfigMessage): String {
        return yamlConfiguration.getString(path.name.lowercase()) ?: return "null"
    }

    private fun loadMessages(){
        path = "messages${File.separator}${main.config.getString("language")}.yml"
        file = File(main.dataFolder, path)
        if (file.exists()) {
            yamlConfiguration = YamlConfiguration.loadConfiguration(file)
        } else {
            main.logger.severe("KoChat stopped working as an invalid language file was selected in config.yml!")
        }
    }

    fun reloadAll(){
        main.reloadConfig()
        loadMessages()
    }


}