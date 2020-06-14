package com.quick.tor.database

import com.quick.tor.config.ApplicationConfig
import com.quick.tor.database.adapters.UserDataAccessAdapter
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.database.repository.impl.UserRepositoryMysqlAdapter
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.typesafe.config.Config
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module
import java.util.Properties
import javax.sql.DataSource

val databaseModule = module(createdAtStart = true) {

    // Database
    single<DataSource> {

        val hikari by lazy {
            val dbConfig = get<ApplicationConfig>().config.getConfig("main-db.hikari")
            val props = dbConfig.toProperties()
            val hikariConfig = HikariConfig(props)
            hikariConfig
        }

        // Data source. We use 1 data source per 1 database. One data source may supply multiple connections.
        HikariDataSource(hikari)
    }

    single {
        DatabaseConnector(dataSource = get())
    }

    single<UserRepositoryPort> {
        UserRepositoryMysqlAdapter(
            dbConnector = get(),
            log = get()
        )
    }

    single<UserDataAccessPort> {
        UserDataAccessAdapter(
            userRepositoryPort = get(),
            log = get()
        )
    }
}

private fun Config.toProperties() = Properties().also { prop ->
    for (e in this.entrySet()) {
        prop.setProperty(e.key, this.getString(e.key))
    }
}