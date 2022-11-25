package com.swg.timeprint.bean

import java.io.Serializable

class ScanBean(
    var owner: String = "",
    var name: String = "",
    var desc: String = "",
    var replaceOpcode: Int = 0,
    var replaceOwner: String = "",
    var replaceName: String = "",
    var replaceDesc: String = ""
) : Cloneable, Serializable {

    override fun clone(): ScanBean {
        return try {
            super.clone() as ScanBean
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
            ScanBean()
        }
    }

}