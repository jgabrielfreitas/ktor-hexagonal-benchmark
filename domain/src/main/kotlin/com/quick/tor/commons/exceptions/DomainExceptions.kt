package com.quick.tor.commons.exceptions

class UserNotificationException(id: String, exc: Exception) : RuntimeException(id, exc)
class UserNotFoundException(id: String) : RuntimeException(message = "user with id $id not found")
class SomeDomainException(id: String) : RuntimeException(id)