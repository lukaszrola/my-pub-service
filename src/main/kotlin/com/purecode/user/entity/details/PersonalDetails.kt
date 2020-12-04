package com.purecode.user.entity.details

import java.time.LocalDate

class PersonalDetails (
        val name: String,
        val sex: Sex,
        val dateOfBirth: LocalDate,
        val relationshipStatus: RelationshipStatus)