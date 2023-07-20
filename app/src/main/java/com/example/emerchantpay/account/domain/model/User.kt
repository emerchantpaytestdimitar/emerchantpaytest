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
    @SerializedName(UserJsonKeys.LOGIN) val login: String = "",
    @SerializedName(UserJsonKeys.ID) val id: Long = 0,
    @SerializedName(UserJsonKeys.NODE_ID) val nodeId: String = "",
    @SerializedName(UserJsonKeys.AVATAR_URL) var avatarUrl: String? = "",
    @SerializedName(UserJsonKeys.URL) val url: String = "",
    @SerializedName(UserJsonKeys.HTML_URL) val htmlUrl: String = "",
    @SerializedName(UserJsonKeys.FOLLOWERS_URL) val followersUrl: String = "",
    @SerializedName(UserJsonKeys.FOLLOWING_URL) val followingUrl: String = "",
    @SerializedName(UserJsonKeys.GISTS_URL) val gistsUrl: String = "",
    @SerializedName(UserJsonKeys.STARRED_URL) val starredUrl: String = "",
    @SerializedName(UserJsonKeys.SUBSCRIPTIONS_URL) val subscriptionsUrl: String = "",
    @SerializedName(UserJsonKeys.ORGANIZATIONS_URL) val organizationsUrl: String = "",
    @SerializedName(UserJsonKeys.REPOS_URL) val reposUrl: String = "",
    @SerializedName(UserJsonKeys.EVENTS_URL) val eventsUrl: String = "",
    @SerializedName(UserJsonKeys.RECEIVED_EVENTS_URL) val receivedEventsUrl: String = "",
    @SerializedName(UserJsonKeys.TYPE) val type: String = "",
    @SerializedName(UserJsonKeys.SITE_ADMIN) val siteAdmin: Boolean = false,
    @SerializedName(UserJsonKeys.NAME) val name: String? = "",
    @SerializedName(UserJsonKeys.COMPANY) val company: String? = "",
    @SerializedName(UserJsonKeys.BLOG) val blog: String = "",
    @SerializedName(UserJsonKeys.LOCATION) val location: String? = "",
    @SerializedName(UserJsonKeys.EMAIL) val email: String? = "",
    @SerializedName(UserJsonKeys.HIREABLE) val hireable: Boolean? = false,
    @SerializedName(UserJsonKeys.BIO) val bio: String? = "",
    @SerializedName(UserJsonKeys.TWITTER_USERNAME) val twitterUsername: String? = "",
    @SerializedName(UserJsonKeys.PUBLIC_REPOS) val publicRepos: Int = 0,
    @SerializedName(UserJsonKeys.PUBLIC_GISTS) val publicGists: Int = 0,
    @SerializedName(UserJsonKeys.FOLLOWERS) val followers: Int = 0,
    @SerializedName(UserJsonKeys.FOLLOWING) val following: Int = 0,
    @SerializedName(UserJsonKeys.CREATED_AT) val createdAt: String = "",
    @SerializedName(UserJsonKeys.UPDATED_AT) val updatedAt: String = ""
) : Parcelable

