package com.purecode.user.controller.body

import com.purecode.user.entity.contact.Contact
import io.micronaut.core.annotation.Introspected

@Introspected
data class ContactBody (val emailAddress: String, val phoneNumber: String = "") {
    constructor(user: Contact) : this(
            emailAddress = user.email.value,
            phoneNumber = user.phone.value
    )
}