package com.github.tsvetilian.ty.androidpermissionstester.core

object Language {
    const val TITLE = "Permissions Tester"
    const val NO_DEVICE_AVAILABLE = "No Device Available"

    // Notifications
    const val NOTIFICATION_TITLE = "Permission Change"
    const val GRANT_ALL_LABEL = "Grant All"
    const val REVOKE_ALL_LABEL = "Revoke All"
    const val ALL_PERMISSIONS_GRANTED_MESSAGE = "All permissions granted"
    const val ALL_PERMISSIONS_REVOKED_MESSAGE = "All permissions revoked"

    // Table
    const val NO_PERMISSIONS_DETECTED = "No permissions detected"
    const val TOOLTIP_GRANT = "Grant Permission"
    const val TOOLTIP_REVOKE = "Revoke Permission"
    const val PERMISSION_COLUMN_LABEL = "Permission"
    const val GRANT_COLUMN_LABEL = "Grant"
    const val REVOKE_COLUMN_LABEL = "Revoke"

    // Success
    fun getPermissionGrantedMessage(permission: String?, packageName: String?) = "Granted $permission to $packageName"
    fun getPermissionRevokeMessage(permission: String?, packageName: String?) = "Revoked $permission from $packageName"

    // Errors
    fun getUnableToGrandPermissionMessage(permission: String?, packageName: String?): String =
        "Unable to grant $permission to $packageName. \n Make sure the application is installed on the device!"

    fun getUnableToRevokePermissionMessage(permission: String?, packageName: String?): String =
        "Unable to revoke $permission from $packageName. \n Make sure the application is installed on the device!"

    fun getUnableToGrandAllPermissionsMessage(packageName: String?): String =
        "Unable to grant all permissions to $packageName \n Make sure the application is installed on the device!"

    fun getUnableToRevokeAllPermissionsMessage(packageName: String?): String =
        "Unable to revoke all permissions from $packageName \n Make sure the application is installed on the device!"
}
