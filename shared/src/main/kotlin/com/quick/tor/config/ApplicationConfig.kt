package com.quick.tor.config

class ApplicationConfig(configRepository: ConfigRepository) {

    val config = configRepository.config.getConfig("app-config")
}
