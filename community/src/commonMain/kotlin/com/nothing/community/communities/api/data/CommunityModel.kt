package com.nothing.community.communities.api.data

data class CommunityModel(
    val name: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int
)