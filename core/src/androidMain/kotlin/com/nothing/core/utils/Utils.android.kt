package com.nothing.core.utils

import android.os.Build

actual fun isAndroid31OrHigher(): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
}

actual fun getPlatformName(): String = "Android"
