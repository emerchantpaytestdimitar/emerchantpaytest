package com.example.emerchantpay.account.domain.model

import com.example.emerchantpay.account.data.local.UserDb

object ConverterUserUtil {

    fun convertUserToUserDb(user: User): UserDb {
        return UserDb(
            id = user.id,
            login = user.login,
            nodeId = user.nodeId,
            avatarUrl = user.avatarUrl,
            url = user.url,
            htmlUrl = user.htmlUrl,
            followersUrl = user.followersUrl,
            followingUrl = user.followingUrl,
            gistsUrl = user.gistsUrl,
            starredUrl = user.starredUrl,
            subscriptionsUrl = user.subscriptionsUrl,
            organizationsUrl = user.organizationsUrl,
            reposUrl = user.reposUrl,
            eventsUrl = user.eventsUrl,
            receivedEventsUrl = user.receivedEventsUrl,
            type = user.type,
            siteAdmin = user.siteAdmin,
            name = user.name,
            company = user.company,
            blog = user.blog,
            location = user.location,
            email = user.email,
            hireable = user.hireable,
            bio = user.bio,
            twitterUsername = user.twitterUsername,
            publicRepos = user.publicRepos,
            publicGists = user.publicGists,
            followers = user.followers,
            following = user.following,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }

    fun convertUserDbToUser(userDb: UserDb): User {
        return User(
            id = userDb.id,
            login = userDb.login,
            nodeId = userDb.nodeId,
            avatarUrl = userDb.avatarUrl,
            url = userDb.url,
            htmlUrl = userDb.htmlUrl,
            followersUrl = userDb.followersUrl,
            followingUrl = userDb.followingUrl,
            gistsUrl = userDb.gistsUrl,
            starredUrl = userDb.starredUrl,
            subscriptionsUrl = userDb.subscriptionsUrl,
            organizationsUrl = userDb.organizationsUrl,
            reposUrl = userDb.reposUrl,
            eventsUrl = userDb.eventsUrl,
            receivedEventsUrl = userDb.receivedEventsUrl,
            type = userDb.type,
            siteAdmin = userDb.siteAdmin,
            name = userDb.name,
            company = userDb.company,
            blog = userDb.blog,
            location = userDb.location,
            email = userDb.email,
            hireable = userDb.hireable,
            bio = userDb.bio,
            twitterUsername = userDb.twitterUsername,
            publicRepos = userDb.publicRepos,
            publicGists = userDb.publicGists,
            followers = userDb.followers,
            following = userDb.following,
            createdAt = userDb.createdAt,
            updatedAt = userDb.updatedAt
        )
    }

    fun convertUserListToUserDbList(userList: List<User>): List<UserDb> {
        return userList.map { convertUserToUserDb(it) }
    }

    fun convertUserDbListToUserList(userDbList: List<UserDb>): List<User> {
        return userDbList.map { convertUserDbToUser(it) }
    }
}