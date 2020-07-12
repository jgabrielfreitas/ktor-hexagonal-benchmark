package com.quick.tor.usecases.user

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.quick.tor.log.Logger
import com.quick.tor.usecases.user.port.secondary.UserDataAccessPort
import com.quick.tor.usecases.user.port.secondary.UserEventDataAccessPort
import com.quick.tor.usecases.user.port.secondary.UserNotificationPort
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class UserUseCaseTest {

    @Mock
    private lateinit var notificationPort: UserNotificationPort

    @Mock
    private lateinit var dataAccessPort: UserDataAccessPort

    @Mock
    private lateinit var eventDataAccessPort: UserEventDataAccessPort

    @Mock
    private lateinit var log: Logger

    private lateinit var userUseCase: UserUseCase

    @Before
    fun setUp() {
        initMocks(this)

        userUseCase = UserUseCase(
            notificationPort,
            dataAccessPort,
            eventDataAccessPort,
            log
        )
    }

    @Test
    fun `save should return saved user correctly when idempotencyId was found`() = runWithCoroutineTest {

        When calling dataAccessPort.findByIdempotency(idempotencyId) itReturns savedUser
        savedUser shouldBeEqualTo userUseCase.save(user)
    }

    @Test
    fun `save should saved event correctly`() = runWithCoroutineTest {

        When calling dataAccessPort.findByIdempotency(idempotencyId) itReturns null
        When calling dataAccessPort.save(user) itReturns savedUser

        userUseCase.save(user)

        Verify on eventDataAccessPort that eventDataAccessPort.save(event) was called
    }

    @Test
    fun `save should notify, delete event and update user status`() = runWithCoroutineTest {

        When calling dataAccessPort.findByIdempotency(idempotencyId) itReturns null
        When calling dataAccessPort.save(user) itReturns savedUser
        When calling eventDataAccessPort.save(event) itReturns event

        userUseCase.save(user)

        Verify on notificationPort that notificationPort.notify(event) was called
        Verify on eventDataAccessPort that eventDataAccessPort.delete(event) was called
        Verify on dataAccessPort that dataAccessPort.update(savedUser.withSendNotificationValidated()) was called
    }

    @Test
    fun `update should notify and delete event`() = runWithCoroutineTest {

        When calling dataAccessPort.update(user) itReturns savedUser
        When calling eventDataAccessPort.save(event) itReturns event

        userUseCase.save(user)

        Verify on notificationPort that notificationPort.notify(event) was called
        Verify on eventDataAccessPort that eventDataAccessPort.delete(event) was called
    }

}
