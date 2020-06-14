package com.quick.tor.config

import com.typesafe.config.Config
import java.util.*

class ApplicationConfig(configRepository: ConfigRepository) {

    val config = configRepository.config.getConfig("app-config")
}
