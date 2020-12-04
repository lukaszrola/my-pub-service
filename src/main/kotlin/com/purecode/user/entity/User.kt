package com.purecode.user.entity

import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.preferences.PubPreferences
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader

class User(
        val id: UserId,
        val email: EmailAddress,
        val password: Password,
        val phone: PhoneNumber,
        val pubPreferences: PubPreferences,
        val personalDetails: PersonalDetails,

)