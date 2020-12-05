package com.purecode.user.controller.body

import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.details.RelationshipStatus
import com.purecode.user.entity.details.Sex
import io.micronaut.core.annotation.Introspected

@Introspected
data class PersonalDetailsBody(
        val name: String,
        val sex: Sex,
        val dateOfBirth: DateBody,
        val relationshipStatus: RelationshipStatus) {

    constructor(personalDetails: PersonalDetails) : this(
            name = personalDetails.name,
            sex = personalDetails.sex,
            dateOfBirth = DateBody(
                    year = personalDetails.dateOfBirth.year,
                    month = personalDetails.dateOfBirth.monthValue,
                    day = personalDetails.dateOfBirth.dayOfMonth
            ),
            relationshipStatus = personalDetails.relationshipStatus
    )
}
