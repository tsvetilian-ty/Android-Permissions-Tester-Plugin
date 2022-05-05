package com.github.tsvetilian.ty.androidpermissionstester.ui.helper

import com.intellij.openapi.util.IconLoader

object PermissionTesterIcons {
    @JvmField
    val GrantPermission = IconLoader.getIcon("/actions/intentionBulb.svg", javaClass)
    @JvmField
    val RevokePermission = IconLoader.getIcon("/actions/quickfixBulb.svg", javaClass)
    @JvmField
    val Icon = IconLoader.getIcon("/icon.svg", javaClass)
}