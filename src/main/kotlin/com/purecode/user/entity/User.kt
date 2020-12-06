package com.purecode.user.entity

import com.purecode.user.entity.alcohol.Alcohol
import com.purecode.user.entity.contact.Contact
import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.preferences.PubPreferences
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationAndConstantLoader

class User(
        val id: UserId,
        val password: Password,
        val contact: Contact,
        val pubPreferences: PubPreferences,
        val personalDetails: PersonalDetails,
        val favoriteAlcohols: List<Alcohol>

) : UserOperationResult