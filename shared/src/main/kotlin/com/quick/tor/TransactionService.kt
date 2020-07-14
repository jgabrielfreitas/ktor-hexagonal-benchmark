package com.quick.tor

interface TransactionService {
    /** Always starts new transaction. */
    @RequiresTransactionContext
    suspend fun <T> newTransaction(block: suspend () -> T): T

    /** Always requires to run inside already started transaction. */
    @RequiresTransactionContext
    suspend fun <T> existingTransaction(block: suspend () -> T): T

    /** Starts new transaction if it has not been started yet or run in existing transaction if it is available. */
    @RequiresTransactionContext
    suspend fun <T> transaction(block: suspend () -> T): T
}