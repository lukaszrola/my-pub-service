package com.purecode.user.controller.body

import com.purecode.user.entity.User
import io.micronaut.core.annotation.Introspected

@Introspected
data class ContactBody (val emailAddress: String, val phoneNumber: String = "") {
    constructor(user: User) : this(
            emailAddress = user.email.value,
            phoneNumber = user.phone.value
    )
}