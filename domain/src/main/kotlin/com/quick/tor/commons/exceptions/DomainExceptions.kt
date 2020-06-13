package com.quick.tor.commons.exceptions

class UserNotificationException(id: String, exc: Exception) : RuntimeException(id, exc)
class UserNotFoundException(id: String) : RuntimeException(id)
class SomeDomainException(id: String) : RuntimeException(id)