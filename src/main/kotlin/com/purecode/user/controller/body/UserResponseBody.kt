package com.purecode.user.controller.body

import com.purecode.user.entity.User
import io.micronaut.core.annotation.Introspected

@Introspected
data class UserResponseBody(
        val contact: ContactBody,
        val personalDetails: PersonalDetailsBody,
        val pubPreferences: PubPreferencesBody,
        val favoriteAlcohols: List<AlcoholBody>
) {
    constructor(user: User) : this(
            contact = ContactBody(user.contact),
            personalDetails = PersonalDetailsBody(user.personalDetails),
            pubPreferences = PubPreferencesBody(user.pubPreferences),
            favoriteAlcohols = user.favoriteAlcohols.map { AlcoholBody(it) }
    )
}