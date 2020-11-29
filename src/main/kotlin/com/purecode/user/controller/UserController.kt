package com.purecode.user.controller

import com.purecode.user.controller.body.*
import com.purecode.user.entity.*
import com.purecode.user.entity.preferences.*
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("users")
class UserController {

    @Get
    fun getUser(): UserResponseBody = UserResponseBody(
            contact = ContactBody(
                    emailAddress = "lukasz.rola@wp.pl",
                    phoneNumber = "555 532 532"
            ),
            personalDetails = PersonalDetailsBody(
                    name = "Łukasz Rola",
                    sex = Sex.MALE,
                    dateOfBirth = DateBody(
                            year = 1992,
                            month = 2,
                            day = 8
                    ),
                    relationshipStatus = RelationshipStatus.MARRIED
            ),
            pubPreferences = PubPreferencesBody(
                    alcoholKind = AlcoholKind.BEER,
                    musicKind = MusicKind.ROCK,
                    pubSize = PubSize.MEDIUM,
                    pubCongestion = PubCongestion.LOW,
                    SmokingPermission.NO_SMOKING
            ),
            favoriteAlcohols = listOf(
                    Alcohol(
                            name = "Salamander",
                            brand = "Browar stu mostów",
                            kind = AlcoholKind.BEER
                    ),
                    Alcohol(
                            name = "Porter bałtycki",
                            brand = "Komes",
                            kind = AlcoholKind.BEER
                    ),
                    Alcohol(
                            name = "Khvanchkara",
                            brand = "Georgian Valley",
                            kind = AlcoholKind.WINE
                    )
            )
    )
}