package com.quick.tor.usecases.user

import com.quick.tor.commons.exceptions.UserNotFoundException
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.primary.UserPort
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.quick.tor.usecases.user.model.User
import java.util.UUID

class UserUseCase(
    private val userDataAccessPort: UserDataAccessPort,
    private val log: Logger
): UserPort {
    override suspend fun save(user: User): User {

        val existsUser = userDataAccessPort.findByIdempotency(user.idempotencyId)
        if (existsUser !== null) return existsUser

        /** future code */
//        val savedUser = userDataAccessPort.save(user)
//        val event = UserEvent(user = savedUser)
//        val savedEvent = userEventDataAccessPort.save(event)
//
//        return try {
//            userNotificationPort.notify(savedEvent)
//            userEventDataAccessPort.delete(savedEvent)
//            userDataAccessPort.update(savedUser.withSendNotificationValidated())
//        } catch (exception: Exception) {
//            // TODO retry
//            logger.error("Error in event", exception)
//            savedUser
//        }

        return userDataAccessPort.save(user.withSendNotificationValidated())
    }

    override suspend fun findById(id: UUID): User? {

        log.info("trying to find user with id: $id")
        val userFound = userDataAccessPort.findById(id)
        log.info("found user: $userFound")

        return userFound
    }

    override suspend fun update(user: User): User? {

        val updated = userDataAccessPort.update(user)
        log.info("Updated user: ${user.id}")

        /** future code */
//        val eventUpdated = userEventDataAccessPort.save(UserEvent(user = updated))
//
//        logger.info("Updated user: ${user.id}")
//
//        try {
//            userNotificationPort.notify(eventUpdated)
//            userEventDataAccessPort.delete(eventUpdated)
//        } catch (exception: Exception) {
//            logger.error("Error in event", exception)
//            // TODO retry
//        }
        return updated
    }

}