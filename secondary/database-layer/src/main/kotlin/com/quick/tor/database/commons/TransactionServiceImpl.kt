package com.quick.tor.database.commons

class TransactionServiceImpl(
    private val dbConnector: DatabaseConnector
) : TransactionService {
    @RequiresTransactionContext
    override suspend fun <T> newTransaction(block: suspend () -> T) = dbConnector.newTransaction {
        block()
    }

    @RequiresTransactionContext
    override suspend fun <T> existingTransaction(block: suspend () -> T) = dbConnector.existingTransaction {
        block()
    }

    @RequiresTransactionContext
    override suspend fun <T> transaction(block: suspend () -> T) = dbConnector.transaction {
        block()
    }

}