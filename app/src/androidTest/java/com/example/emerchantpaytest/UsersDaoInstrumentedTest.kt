package com.example.emerchantpaytest

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.emerchantpay.account.data.local.UserDb
import com.example.emerchantpay.account.data.local.UsersDao
import com.example.emerchantpay.common.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class UsersDaoInstrumentedTest {

    private lateinit var usersDao: UsersDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        usersDao = db.usesrDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetUser() = runBlocking {
        val user = UserDb(
            id = 1L,
            login = "testUser",
            nodeId = "node1",
            avatarUrl = "avatar_url",
            url = "url",
            htmlUrl = "html_url",
            followersUrl = "followers_url",
            followingUrl = "following_url",
            gistsUrl = "gists_url",
            starredUrl = "starred_url",
            subscriptionsUrl = "subscriptions_url",
            organizationsUrl = "organizations_url",
            reposUrl = "repos_url",
            eventsUrl = "events_url",
            receivedEventsUrl = "received_events_url",
            type = "User",
            siteAdmin = false,
            name = "Test User",
            company = "Test Company",
            blog = "blog",
            location = "Test Location",
            email = "test@example.com",
            hireable = true,
            bio = "bio",
            twitterUsername = "twitter_username",
            publicRepos = 10,
            publicGists = 5,
            followers = 100,
            following = 50,
            createdAt = "2023-07-24T00:00:00Z",
            updatedAt = "2023-07-24T00:00:00Z"
        )
        usersDao.insertUser(user)
        val byId = usersDao.getUserById(1)
        assertThat(byId, equalTo(user))
    }

    @Test
    fun testSearchUserByName() = runBlocking {
        val user1 = UserDb(
            id = 1L,
            login = "test_user_1",
            nodeId = "node1",
            avatarUrl = "avatar_url",
            url = "url",
            htmlUrl = "html_url",
            followersUrl = "followers_url",
            followingUrl = "following_url",
            gistsUrl = "gists_url",
            starredUrl = "starred_url",
            subscriptionsUrl = "subscriptions_url",
            organizationsUrl = "organizations_url",
            reposUrl = "repos_url",
            eventsUrl = "events_url",
            receivedEventsUrl = "received_events_url",
            type = "User",
            siteAdmin = false,
            name = "Test User",
            company = "Test Company",
            blog = "blog",
            location = "Test Location",
            email = "test@example.com",
            hireable = true,
            bio = "bio",
            twitterUsername = "twitter_username",
            publicRepos = 10,
            publicGists = 5,
            followers = 100,
            following = 50,
            createdAt = "2023-07-24T00:00:00Z",
            updatedAt = "2023-07-24T00:00:00Z"
        )
        val user2 = UserDb(
            id = 2L,
            login = "test_user_2",
            nodeId = "node1",
            avatarUrl = "avatar_url",
            url = "url",
            htmlUrl = "html_url",
            followersUrl = "followers_url",
            followingUrl = "following_url",
            gistsUrl = "gists_url",
            starredUrl = "starred_url",
            subscriptionsUrl = "subscriptions_url",
            organizationsUrl = "organizations_url",
            reposUrl = "repos_url",
            eventsUrl = "events_url",
            receivedEventsUrl = "received_events_url",
            type = "User",
            siteAdmin = false,
            name = "Test User",
            company = "Test Company",
            blog = "blog",
            location = "Test Location",
            email = "test@example.com",
            hireable = true,
            bio = "bio",
            twitterUsername = "twitter_username",
            publicRepos = 10,
            publicGists = 5,
            followers = 100,
            following = 50,
            createdAt = "2023-07-24T00:00:00Z",
            updatedAt = "2023-07-24T00:00:00Z"
        )
        usersDao.insertUser(user1)
        usersDao.insertUser(user2)

        val searchResult = usersDao.searchUserByName("test_user_1")

        assertThat(searchResult, equalTo(listOf(user1)))
    }

    @Test
    fun testGetUsersByFollowedByUserId() = runBlocking {
        val user1 = UserDb(
            id = 1L,
            login = "test_user_1",
            nodeId = "node1",
            avatarUrl = "avatar_url",
            url = "url",
            htmlUrl = "html_url",
            followersUrl = "followers_url",
            followingUrl = "following_url",
            gistsUrl = "gists_url",
            starredUrl = "starred_url",
            subscriptionsUrl = "subscriptions_url",
            organizationsUrl = "organizations_url",
            reposUrl = "repos_url",
            eventsUrl = "events_url",
            receivedEventsUrl = "received_events_url",
            type = "User",
            siteAdmin = false,
            name = "Test User",
            company = "Test Company",
            blog = "blog",
            location = "Test Location",
            email = "test@example.com",
            hireable = true,
            bio = "bio",
            twitterUsername = "twitter_username",
            publicRepos = 10,
            publicGists = 5,
            followers = 100,
            following = 50,
            createdAt = "2023-07-24T00:00:00Z",
            updatedAt = "2023-07-24T00:00:00Z",
            followedByUserId = 10
        )
        val user2 = UserDb(
            id = 2L,
            login = "test_user_2",
            nodeId = "node1",
            avatarUrl = "avatar_url",
            url = "url",
            htmlUrl = "html_url",
            followersUrl = "followers_url",
            followingUrl = "following_url",
            gistsUrl = "gists_url",
            starredUrl = "starred_url",
            subscriptionsUrl = "subscriptions_url",
            organizationsUrl = "organizations_url",
            reposUrl = "repos_url",
            eventsUrl = "events_url",
            receivedEventsUrl = "received_events_url",
            type = "User",
            siteAdmin = false,
            name = "Test User",
            company = "Test Company",
            blog = "blog",
            location = "Test Location",
            email = "test@example.com",
            hireable = true,
            bio = "bio",
            twitterUsername = "twitter_username",
            publicRepos = 10,
            publicGists = 5,
            followers = 100,
            following = 50,
            createdAt = "2023-07-24T00:00:00Z",
            updatedAt = "2023-07-24T00:00:00Z",
            followedByUserId = 20
        )
        usersDao.insertUser(user1)
        usersDao.insertUser(user2)

        val users = usersDao.getUsersByFollowedByUserId(10)
        assertThat(users, equalTo(listOf(user1)))
    }
}
