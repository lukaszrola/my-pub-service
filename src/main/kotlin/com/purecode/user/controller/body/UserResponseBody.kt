package com.purecode.user.controller.body

import com.purecode.user.entity.User
import io.micronaut.core.annotation.Introspected

@Introspected
class UserResponseBody(
        val contact: ContactBody,
        val personalDetails: PersonalDetailsBody,
        val pubPreferences: PubPreferencesBody,
        val favoriteAlcoholBodies: List<AlcoholBody>
) {
    constructor(user: User) : this(
            contact = ContactBody(user),
            personalDetails = PersonalDetailsBody(user.personalDetails),
            pubPreferences = PubPreferencesBody(user.pubPreferences),
            favoriteAlcoholBodies = listOf()
    )
}