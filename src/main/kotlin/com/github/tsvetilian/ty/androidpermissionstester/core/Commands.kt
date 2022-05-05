package com.github.tsvetilian.ty.androidpermissionstester.core

object Commands {
    fun grant(packageName: String?, permission: String) = "pm grant $packageName $permission"
    fun revoke(packageName: String?, permission: String) = "pm revoke $packageName $permission"
}