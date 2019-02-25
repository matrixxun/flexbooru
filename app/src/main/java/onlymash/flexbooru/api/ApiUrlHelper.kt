/*
 * Copyright (C) 2019 by onlymash <im@fiepi.me>, All rights reserved
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package onlymash.flexbooru.api

import okhttp3.HttpUrl
import onlymash.flexbooru.entity.*

object ApiUrlHelper {
    fun getDanUrl(search: Search, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("posts.json")
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("tags", search.keyword)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("api_key", search.auth_key)
            .build()
    }

    fun getMoeUrl(search: Search, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("post.json")
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("tags", search.keyword)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("password_hash", search.auth_key)
            .build()
    }

    fun getDanPopularUrl(popular: SearchPopular): HttpUrl {
        return HttpUrl.Builder()
            .scheme(popular.scheme)
            .host(popular.host)
            .addPathSegment("explore")
            .addPathSegment("posts")
            .addPathSegment("popular.json")
            .addQueryParameter("date", popular.date)
            .addQueryParameter("scale", popular.scale)
            .addQueryParameter("login", popular.username)
            .addQueryParameter("api_key", popular.auth_key)
            .build()
    }

    fun getMoePopularUrl(popular: SearchPopular): HttpUrl {
        return HttpUrl.Builder()
            .scheme(popular.scheme)
            .host(popular.host)
            .addPathSegment("post")
            .addPathSegment("popular_recent.json")
            .addQueryParameter("period", popular.period)
            .addQueryParameter("login", popular.username)
            .addQueryParameter("password_hash", popular.auth_key)
            .build()
    }

    fun getDanUserUrl(username: String, booru: Booru): HttpUrl {
        return HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("users.json")
            .addQueryParameter("search[name]", username)
            .build()
    }

    fun getMoeUserUrl(username: String, booru: Booru): HttpUrl {
        return HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("user.json")
            .addQueryParameter("name", username)
            .build()
    }

    fun getMoeUserUrlById(id: Int, booru: Booru): HttpUrl {
        return HttpUrl.Builder()
            .scheme(booru.scheme)
            .host(booru.host)
            .addPathSegment("user.json")
            .addQueryParameter("id", id.toString())
            .build()
    }

    fun getDanPoolUrl(search: Search, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("pools.json")
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("search[name_matches]", search.keyword)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("api_key", search.auth_key)
            .build()
    }

    fun getMoePoolUrl(search: Search, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("pool.json")
            .addQueryParameter("query", search.keyword)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("password_hash", search.auth_key)
            .build()
    }

    fun getDanTagUrl(search: SearchTag, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("tags.json")
            .addQueryParameter("search[name_matches]", search.name)
            .addQueryParameter("search[order]", search.order)
            .addQueryParameter("search[category]", search.type)
            .addQueryParameter("search[hide_empty]", "yes")
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("api_key", search.auth_key)
            .addQueryParameter("commit", "Search")
            .build()
    }

    fun getMoeTagUrl(search: SearchTag, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("tag.json")
            .addQueryParameter("name", search.name)
            .addQueryParameter("order", search.order)
            .addQueryParameter("type", search.type)
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("password_hash", search.auth_key)
            .addQueryParameter("commit", "Search")
            .build()
    }

    fun getDanArtistUrl(search: SearchArtist, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("artists.json")
            .addQueryParameter("search[any_name_matches]", search.name)
            .addQueryParameter("search[order]", search.order)
            .addQueryParameter("limit", search.limit.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("api_key", search.auth_key)
            .addQueryParameter("commit", "Search")
            .build()
    }

    fun getMoeArtistUrl(search: SearchArtist, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(search.scheme)
            .host(search.host)
            .addPathSegment("artist.json")
            .addQueryParameter("name", search.name)
            .addQueryParameter("order", search.order)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", search.username)
            .addQueryParameter("password_hash", search.auth_key)
            .addQueryParameter("commit", "Search")
            .build()
    }

    fun getDanAddFavUrl(vote: Vote): String =
        String.format("%s://%s/favorites.json", vote.scheme, vote.host)

    fun getDanRemoveFavUrl(vote: Vote): HttpUrl {
        return HttpUrl.Builder()
            .scheme(vote.scheme)
            .host(vote.host)
            .addPathSegment("favorites")
            .addPathSegment("${vote.post_id}.json")
            .addQueryParameter("login", vote.username)
            .addQueryParameter("api_key", vote.auth_key)
            .build()
    }

    fun getMoeVoteUrl(vote: Vote): String =
        String.format("%s://%s/post/vote.json", vote.scheme, vote.host)

    fun getMoePostCommentUrl(commentAction: CommentAction, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(commentAction.scheme)
            .host(commentAction.host)
            .addPathSegment("comment.json")
            .addQueryParameter("post_id", commentAction.post_id.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", commentAction.username)
            .addQueryParameter("password_hash", commentAction.auth_key)
            .build()
    }

    fun getMoePostsCommentUrl(commentAction: CommentAction, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(commentAction.scheme)
            .host(commentAction.host)
            .addPathSegment("comment")
            .addPathSegment("search.json")
            .addQueryParameter("query", commentAction.query)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("login", commentAction.username)
            .addQueryParameter("password_hash", commentAction.auth_key)
            .build()
    }

    fun getMoeCreateCommentUrl(commentAction: CommentAction): String =
        String.format("%s://%s/comment/create.json", commentAction.scheme, commentAction.host)

    fun getMoeDestroyCommentUrl(commentAction: CommentAction): String =
        String.format("%s://%s/comment/destroy.json", commentAction.scheme, commentAction.host)

    fun getDanPostCommentUrl(commentAction: CommentAction, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(commentAction.scheme)
            .host(commentAction.host)
            .addPathSegment("comments.json")
            .addQueryParameter("group_by", "comment")
            .addQueryParameter("search[post_id]", commentAction.post_id.toString())
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", commentAction.limit.toString())
            .addQueryParameter("login", commentAction.username)
            .addQueryParameter("api_key", commentAction.auth_key)
            .build()
    }

    fun getDanPostsCommentUrl(commentAction: CommentAction, page: Int): HttpUrl {
        return HttpUrl.Builder()
            .scheme(commentAction.scheme)
            .host(commentAction.host)
            .addPathSegment("comments.json")
            .addQueryParameter("group_by", "comment")
            .addQueryParameter("search[creator_name]", commentAction.query)
            .addQueryParameter("page", page.toString())
            .addQueryParameter("limit", commentAction.limit.toString())
            .addQueryParameter("login", commentAction.username)
            .addQueryParameter("api_key", commentAction.auth_key)
            .build()
    }

    fun getDanDeleteCommentUrl(commentAction: CommentAction): HttpUrl {
        return HttpUrl.Builder()
            .scheme(commentAction.scheme)
            .host(commentAction.host)
            .addPathSegment("comments/${commentAction.comment_id}.json")
            .addQueryParameter("login", commentAction.username)
            .addQueryParameter("api_key", commentAction.auth_key)
            .build()
    }

    fun getDanCreateCommentUrl(commentAction: CommentAction): String =
            String.format("%s://%s/comments.json", commentAction.scheme, commentAction.host)
}
