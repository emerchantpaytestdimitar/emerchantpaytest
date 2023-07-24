package com.example.emerchantpaytest

import com.example.emerchantpay.account.domain.model.ConverterUserUtil
import com.example.emerchantpay.account.domain.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterUserUtilTest {

    private val mockUserList = listOf(
        User(
            id = 1L,
            login = "login1",
            nodeId = "nodeId1",
            avatarUrl = "avatarUrl1",
            url = "url1",
            htmlUrl = "htmlUrl1",
            followersUrl = "followersUrl1",
            followingUrl = "followingUrl1",
            gistsUrl = "gistsUrl1",
            starredUrl = "starredUrl1",
            subscriptionsUrl = "subscriptionsUrl1",
            organizationsUrl = "organizationsUrl1",
            reposUrl = "reposUrl1",
            eventsUrl = "eventsUrl1",
            receivedEventsUrl = "receivedEventsUrl1",
            type = "type1",
            siteAdmin = true,
            name = "name1",
            company = "company1",
            blog = "blog1",
            location = "location1",
            email = "email1",
            hireable = true,
            bio = "bio1",
            twitterUsername = "twitterUsername1",
            publicRepos = 10,
            publicGists = 10,
            followers = 10,
            following = 10,
            createdAt = "createdAt1",
            updatedAt = "updatedAt1"
        )
    )

    private val mockUserDbList = mockUserList.map { ConverterUserUtil.convertUserToUserDb(it) }

    @Test
    fun `test convertUserToUserDb`() {
        val user = mockUserList[0]
        val userDb = ConverterUserUtil.convertUserToUserDb(user)

        assertEquals(user.id, userDb.id)
        assertEquals(user.login, userDb.login)
        assertEquals(user.nodeId, userDb.nodeId)
        assertEquals(user.avatarUrl, userDb.avatarUrl)
        assertEquals(user.url, userDb.url)
        assertEquals(user.htmlUrl, userDb.htmlUrl)
        assertEquals(user.followersUrl, userDb.followersUrl)
        assertEquals(user.followingUrl, userDb.followingUrl)
        assertEquals(user.gistsUrl, userDb.gistsUrl)
        assertEquals(user.starredUrl, userDb.starredUrl)
        assertEquals(user.subscriptionsUrl, userDb.subscriptionsUrl)
        assertEquals(user.organizationsUrl, userDb.organizationsUrl)
        assertEquals(user.reposUrl, userDb.reposUrl)
        assertEquals(user.eventsUrl, userDb.eventsUrl)
        assertEquals(user.receivedEventsUrl, userDb.receivedEventsUrl)
        assertEquals(user.type, userDb.type)
        assertEquals(user.siteAdmin, userDb.siteAdmin)
        assertEquals(user.name, userDb.name)
        assertEquals(user.company, userDb.company)
        assertEquals(user.blog, userDb.blog)
        assertEquals(user.location, userDb.location)
        assertEquals(user.email, userDb.email)
        assertEquals(user.hireable, userDb.hireable)
        assertEquals(user.bio, userDb.bio)
        assertEquals(user.twitterUsername, userDb.twitterUsername)
        assertEquals(user.publicRepos, userDb.publicRepos)
        assertEquals(user.publicGists, userDb.publicGists)
        assertEquals(user.followers, userDb.followers)
        assertEquals(user.following, userDb.following)
        assertEquals(user.createdAt, userDb.createdAt)
        assertEquals(user.updatedAt, userDb.updatedAt)
    }

    @Test
    fun `test convertUserDbToUser`() {
        val userDb = mockUserDbList[0]
        val user = ConverterUserUtil.convertUserDbToUser(userDb)

        assertEquals(userDb.id, user.id)
        assertEquals(userDb.login, user.login)
        assertEquals(userDb.nodeId, user.nodeId)
        assertEquals(userDb.avatarUrl, user.avatarUrl)
        assertEquals(userDb.url, user.url)
        assertEquals(userDb.htmlUrl, user.htmlUrl)
        assertEquals(userDb.followersUrl, user.followersUrl)
        assertEquals(userDb.followingUrl, user.followingUrl)
        assertEquals(userDb.gistsUrl, user.gistsUrl)
        assertEquals(userDb.starredUrl, user.starredUrl)
        assertEquals(userDb.subscriptionsUrl, user.subscriptionsUrl)
        assertEquals(userDb.organizationsUrl, user.organizationsUrl)
        assertEquals(userDb.reposUrl, user.reposUrl)
        assertEquals(userDb.eventsUrl, user.eventsUrl)
        assertEquals(userDb.receivedEventsUrl, user.receivedEventsUrl)
        assertEquals(userDb.type, user.type)
        assertEquals(userDb.siteAdmin, user.siteAdmin)
        assertEquals(userDb.name, user.name)
        assertEquals(userDb.company, user.company)
        assertEquals(userDb.blog, user.blog)
        assertEquals(userDb.location, user.location)
        assertEquals(userDb.email, user.email)
        assertEquals(userDb.hireable, user.hireable)
        assertEquals(userDb.bio, user.bio)
        assertEquals(userDb.twitterUsername, user.twitterUsername)
        assertEquals(userDb.publicRepos, user.publicRepos)
        assertEquals(userDb.publicGists, user.publicGists)
        assertEquals(userDb.followers, user.followers)
        assertEquals(userDb.following, user.following)
        assertEquals(userDb.createdAt, user.createdAt)
        assertEquals(userDb.updatedAt, user.updatedAt)
    }

    @Test
    fun `test convertUserListToUserDbList`() {
        val userDbList = ConverterUserUtil.convertUserListToUserDbList(mockUserList)

        for (i in mockUserList.indices) {
            val user = mockUserList[i]
            val userDb = userDbList[i]

            assertEquals(user.id, userDb.id)
            assertEquals(user.login, userDb.login)
            assertEquals(user.nodeId, userDb.nodeId)
            assertEquals(user.avatarUrl, userDb.avatarUrl)
            assertEquals(user.url, userDb.url)
            assertEquals(user.htmlUrl, userDb.htmlUrl)
            assertEquals(user.followersUrl, userDb.followersUrl)
            assertEquals(user.followingUrl, userDb.followingUrl)
            assertEquals(user.gistsUrl, userDb.gistsUrl)
            assertEquals(user.starredUrl, userDb.starredUrl)
            assertEquals(user.subscriptionsUrl, userDb.subscriptionsUrl)
            assertEquals(user.organizationsUrl, userDb.organizationsUrl)
            assertEquals(user.reposUrl, userDb.reposUrl)
            assertEquals(user.eventsUrl, userDb.eventsUrl)
            assertEquals(user.receivedEventsUrl, userDb.receivedEventsUrl)
            assertEquals(user.type, userDb.type)
            assertEquals(user.siteAdmin, userDb.siteAdmin)
            assertEquals(user.name, userDb.name)
            assertEquals(user.company, userDb.company)
            assertEquals(user.blog, userDb.blog)
            assertEquals(user.location, userDb.location)
            assertEquals(user.email, userDb.email)
            assertEquals(user.hireable, userDb.hireable)
            assertEquals(user.bio, userDb.bio)
            assertEquals(user.twitterUsername, userDb.twitterUsername)
            assertEquals(user.publicRepos, userDb.publicRepos)
            assertEquals(user.publicGists, userDb.publicGists)
            assertEquals(user.followers, userDb.followers)
            assertEquals(user.following, userDb.following)
            assertEquals(user.createdAt, userDb.createdAt)
            assertEquals(user.updatedAt, userDb.updatedAt)
        }
    }

    @Test
    fun `test convertUserDbListToUserList`() {
        val userList = ConverterUserUtil.convertUserDbListToUserList(mockUserDbList)

        for (i in mockUserDbList.indices) {
            val userDb = mockUserDbList[i]
            val user = userList[i]

            assertEquals(userDb.id, user.id)
            assertEquals(userDb.login, user.login)
            assertEquals(userDb.nodeId, user.nodeId)
            assertEquals(userDb.avatarUrl, user.avatarUrl)
            assertEquals(userDb.url, user.url)
            assertEquals(userDb.htmlUrl, user.htmlUrl)
            assertEquals(userDb.followersUrl, user.followersUrl)
            assertEquals(userDb.followingUrl, user.followingUrl)
            assertEquals(userDb.gistsUrl, user.gistsUrl)
            assertEquals(userDb.starredUrl, user.starredUrl)
            assertEquals(userDb.subscriptionsUrl, user.subscriptionsUrl)
            assertEquals(userDb.organizationsUrl, user.organizationsUrl)
            assertEquals(userDb.reposUrl, user.reposUrl)
            assertEquals(userDb.eventsUrl, user.eventsUrl)
            assertEquals(userDb.receivedEventsUrl, user.receivedEventsUrl)
            assertEquals(userDb.type, user.type)
            assertEquals(userDb.siteAdmin, user.siteAdmin)
            assertEquals(userDb.name, user.name)
            assertEquals(userDb.company, user.company)
            assertEquals(userDb.blog, user.blog)
            assertEquals(userDb.location, user.location)
            assertEquals(userDb.email, user.email)
            assertEquals(userDb.hireable, user.hireable)
            assertEquals(userDb.bio, user.bio)
            assertEquals(userDb.twitterUsername, user.twitterUsername)
            assertEquals(userDb.publicRepos, user.publicRepos)
            assertEquals(userDb.publicGists, user.publicGists)
            assertEquals(userDb.followers, user.followers)
            assertEquals(userDb.following, user.following)
            assertEquals(userDb.createdAt, user.createdAt)
            assertEquals(userDb.updatedAt, user.updatedAt)
        }
    }
}
