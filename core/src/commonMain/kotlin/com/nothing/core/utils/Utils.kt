package com.nothing.core.utils

expect fun isAndroid31OrHigher(): Boolean

expect fun getPlatformName(): String

fun isAndroid() : Boolean = getPlatformName() == "Android"
fun isIos() : Boolean = getPlatformName() == "iOS"

object Utils {

    fun getInitials(name: String): String {
        val words = name.trim().split("\\s+".toRegex())
        return when (words.size) {
            0 -> ""
            1 -> words[0].take(1).uppercase()
            else -> words[0].take(1).uppercase() + words[1].take(1).uppercase()
        }
    }

}