package com.example.emerchantpay.account.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.emerchantpay.data.constants.UserJsonKeys
import com.example.emerchantpay.data.constants.UserRoomConstants
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Parcelize
data class User(
    @SerializedName(UserJsonKeys.LOGIN) @ColumnInfo(name = UserRoomConstants.LOGIN) val login: String = "",
    @SerializedName(UserJsonKeys.ID) @ColumnInfo(name = UserRoomConstants.ID) val id: Long = 0,
    @SerializedName(UserJsonKeys.NODE_ID) @ColumnInfo(name = UserRoomConstants.NODE_ID) val nodeId: String = "",
    @SerializedName(UserJsonKeys.AVATAR_URL) @ColumnInfo(name = UserRoomConstants.AVATAR_URL) var avatarUrl: String? = "",
    @SerializedName(UserJsonKeys.URL) @ColumnInfo(name = UserRoomConstants.URL) val url: String = "",
    @SerializedName(UserJsonKeys.HTML_URL) @ColumnInfo(name = UserRoomConstants.HTML_URL) val htmlUrl: String = "",
    @SerializedName(UserJsonKeys.FOLLOWERS_URL) @ColumnInfo(name = UserRoomConstants.FOLLOWERS_URL) val followersUrl: String = "",
    @SerializedName(UserJsonKeys.FOLLOWING_URL) @ColumnInfo(name = UserRoomConstants.FOLLOWING_URL) val followingUrl: String = "",
    @SerializedName(UserJsonKeys.GISTS_URL) @ColumnInfo(name = UserRoomConstants.GISTS_URL) val gistsUrl: String = "",
    @SerializedName(UserJsonKeys.STARRED_URL) @ColumnInfo(name = UserRoomConstants.STARRED_URL) val starredUrl: String = "",
    @SerializedName(UserJsonKeys.SUBSCRIPTIONS_URL) @ColumnInfo(name = UserRoomConstants.SUBSCRIPTIONS_URL) val subscriptionsUrl: String = "",
    @SerializedName(UserJsonKeys.ORGANIZATIONS_URL) @ColumnInfo(name = UserRoomConstants.ORGANIZATIONS_URL) val organizationsUrl: String = "",
    @SerializedName(UserJsonKeys.REPOS_URL) @ColumnInfo(name = UserRoomConstants.REPOS_URL) val reposUrl: String = "",
    @SerializedName(UserJsonKeys.EVENTS_URL) @ColumnInfo(name = UserRoomConstants.EVENTS_URL) val eventsUrl: String = "",
    @SerializedName(UserJsonKeys.RECEIVED_EVENTS_URL) @ColumnInfo(name = UserRoomConstants.RECEIVED_EVENTS_URL) val receivedEventsUrl: String = "",
    @SerializedName(UserJsonKeys.TYPE) @ColumnInfo(name = UserRoomConstants.TYPE) val type: String = "",
    @SerializedName(UserJsonKeys.SITE_ADMIN) @ColumnInfo(name = UserRoomConstants.SITE_ADMIN) val siteAdmin: Boolean = false,
    @SerializedName(UserJsonKeys.NAME) @ColumnInfo(name = UserRoomConstants.NAME) val name: String? = "",
    @SerializedName(UserJsonKeys.COMPANY) @ColumnInfo(name = UserRoomConstants.COMPANY) val company: String? = "",
    @SerializedName(UserJsonKeys.BLOG) @ColumnInfo(name = UserRoomConstants.BLOG) val blog: String = "",
    @SerializedName(UserJsonKeys.LOCATION) @ColumnInfo(name = UserRoomConstants.LOCATION) val location: String? = "",
    @SerializedName(UserJsonKeys.EMAIL) @ColumnInfo(name = UserRoomConstants.EMAIL) val email: String? = "",
    @SerializedName(UserJsonKeys.HIREABLE) @ColumnInfo(name = UserRoomConstants.HIREABLE) val hireable: Boolean? = false,
    @SerializedName(UserJsonKeys.BIO) @ColumnInfo(name = UserRoomConstants.BIO) val bio: String? = "",
    @SerializedName(UserJsonKeys.TWITTER_USERNAME) @ColumnInfo(name = UserRoomConstants.TWITTER_USERNAME) val twitterUsername: String? = "",
    @SerializedName(UserJsonKeys.PUBLIC_REPOS) @ColumnInfo(name = UserRoomConstants.PUBLIC_REPOS) val publicRepos: Int = 0,
    @SerializedName(UserJsonKeys.PUBLIC_GISTS) @ColumnInfo(name = UserRoomConstants.PUBLIC_GISTS) val publicGists: Int = 0,
    @SerializedName(UserJsonKeys.FOLLOWERS) @ColumnInfo(name = UserRoomConstants.FOLLOWERS) val followers: Int = -1,
    @SerializedName(UserJsonKeys.FOLLOWING) @ColumnInfo(name = UserRoomConstants.FOLLOWING) val following: Int = -1,
    @SerializedName(UserJsonKeys.CREATED_AT) @ColumnInfo(name = UserRoomConstants.CREATED_AT) val createdAt: String = "",
    @SerializedName(UserJsonKeys.UPDATED_AT) @ColumnInfo(name = UserRoomConstants.UPDATED_AT) val updatedAt: String = ""
) : Parcelable

