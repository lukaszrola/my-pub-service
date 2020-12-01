package com.purecode.user.entity

import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.preferences.PubPreferences

class User(
        val id: UserId,
        val email: EmailAddress,
        val password: Password,
        val phone: String,
        val pubPreferences: PubPreferences,
        val personalDetails: PersonalDetails,
)