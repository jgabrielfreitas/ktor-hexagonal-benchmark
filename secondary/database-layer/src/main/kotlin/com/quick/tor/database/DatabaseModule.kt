package com.quick.tor.database

import com.quick.tor.config.ApplicationConfig
import com.quick.tor.database.adapters.UserDataAccessAdapter
import com.quick.tor.database.adapters.UserEventDataAccessAdapter
import com.quick.tor.database.commons.DatabaseConnector
import com.quick.tor.database.repository.EventRepositoryPort
import com.quick.tor.database.repository.UserRepositoryPort
import com.quick.tor.database.repository.impl.EventRepositoryMysqlAdapter
import com.quick.tor.database.repository.impl.UserRepositoryMysqlAdapter
import com.quick.tor.domainModule
import com.quick.tor.sharedModule
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.quick.tor.usecases.user.port.secondary.UserEventDataAccessPort
import com.typesafe.config.Config
import com.viartemev.ktor.flyway.FlywayFeature
import com.viartemev.ktor.flyway.Migrate
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.util.KtorExperimentalAPI
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
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

    single<EventRepositoryPort> {
        EventRepositoryMysqlAdapter(
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

    single<UserEventDataAccessPort> {
        UserEventDataAccessAdapter(
            logger = get(),
            eventRepositoryPort = get()
        )
    }

}

fun Application.installFlyway() {
    install(FlywayFeature) {
        dataSource = get()
        commands(Migrate)
    }
}

private fun Config.toProperties() = Properties().also { prop ->
    for (e in this.entrySet()) {
        prop.setProperty(e.key, this.getString(e.key))
    }
}