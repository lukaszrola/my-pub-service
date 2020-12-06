package com.purecode.user.repository

import com.purecode.user.entity.*
import com.purecode.user.entity.contact.Contact
import com.purecode.user.entity.details.PersonalDetails
import com.purecode.user.entity.details.RelationshipStatus
import com.purecode.user.entity.details.Sex
import com.purecode.user.entity.preferences.*
import java.time.LocalDate
import javax.inject.Singleton

@Singleton
class UserInMemoryRepository : UserRepository {
    private val users: MutableList<User> = mutableListOf(
        User(
            id = UserId("1"),

            password = Password("somePassword"),
            contact = Contact(
                email = EmailAddress("lukasz.rola@wp.pl"),
                phone = PhoneNumber("523 543 432")
            ),
            personalDetails = PersonalDetails(
                name = "Łukasz Rola",
                sex = Sex.MALE,
                dateOfBirth = LocalDate.of(1992, 2, 8),
                relationshipStatus = RelationshipStatus.MARRIED
            ),
            pubPreferences = PubPreferences(
                alcoholKind = AlcoholKind.BEER,
                musicKind = MusicKind.ROCK,
                pubSize = PubSize.MEDIUM,
                pubCongestion = PubCongestion.LOW,
                smokingPermission = SmokingPermission.NO_SMOKING
            )
        )
    )

    override fun addNewUser(user: User): User {
        users.add(user)
        return user
    }

    override fun findUserByEmail(emailAddress: EmailAddress): User? = users.firstOrNull() { it.contact.email == emailAddress }


}