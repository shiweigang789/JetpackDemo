package com.swg.jetpack

object DoubleClickUtils {

    private var sLastCLickTime: Long = 0

    fun canClick(duration: Long = 500): Boolean {
        val now = System.currentTimeMillis()
        if (now - sLastCLickTime > duration) {
            sLastCLickTime = now
            return true
        }
        return false
    }

}