package com.swg.jetpack

object DoubleClickUtils {

    private var sLastCLickTime: Long = 0

    fun inDoubleClick(duration: Long): Boolean {
        val now = System.currentTimeMillis()
        val canClick = sLastCLickTime != 0L && now - sLastCLickTime <= duration
        sLastCLickTime = now
        return canClick
    }

}