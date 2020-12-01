package com.purecode.user.entity.details

import com.purecode.user.controller.body.DateBody

class PersonalDetails (
        val name: String,
        val sex: Sex,
        val dateOfBirth: DateBody,
        val relationshipStatus: RelationshipStatus)