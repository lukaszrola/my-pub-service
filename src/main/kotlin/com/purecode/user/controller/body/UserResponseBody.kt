package com.purecode.user.controller.body

import com.purecode.user.entity.preferences.Alcohol
import io.micronaut.core.annotation.Introspected

@Introspected
class UserResponseBody(
        val contact: ContactBody,
        val personalDetails: PersonalDetailsBody,
        val pubPreferences: PubPreferencesBody,
        val favoriteAlcohols: List<Alcohol>
)