package com.quick.tor.database.commons

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransaction
import javax.sql.DataSource

class DatabaseConnector(
    dataSource: DataSource
) {
    val db: Database = Database.connect(datasource = dataSource)

    @RequiresTransactionContext
    suspend fun <T> newTransaction(
        block: suspend (tx: Transaction) -> T
    ): T {
        return newSuspendedTransaction(db = db) {
            block(this)
        }
    }

    @RequiresTransactionContext
    suspend fun <T> existingTransaction(block: suspend (tx: Transaction) -> T): T {
        val tx = TransactionManager.current()
        return tx.suspendedTransaction {
            block(this)
        }
    }

    @RequiresTransactionContext
    suspend fun <T> transaction(block: suspend (tx: Transaction) -> T): T {
        val tx = TransactionManager.currentOrNull()
        return if (tx == null || tx.connection.isClosed) {
            newSuspendedTransaction(db = db) {
                block(this)
            }
        } else {
            tx.suspendedTransaction {
                block(this)
            }
        }
    }
}