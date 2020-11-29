package com.purecode.user.controller.body

import io.micronaut.core.annotation.Introspected

@Introspected
class ContactBody (val emailAddress: String, val phoneNumber: String = "")