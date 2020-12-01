package com.purecode.user.controller.body

import io.micronaut.core.annotation.Introspected

@Introspected
class UserResponseBody(
        val contact: ContactBody,
        val personalDetails: PersonalDetailsBody,
        val pubPreferences: PubPreferencesBody,
        val favoriteAlcoholBodies: List<AlcoholBody>
)