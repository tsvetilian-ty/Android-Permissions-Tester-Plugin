package com.github.tsvetilian.ty.androidpermissionstester.core

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

fun Project?.notify(title: String, message: String, type: NotificationType = NotificationType.INFORMATION) {
    NotificationGroupManager.getInstance()
        .getNotificationGroup("Android Permissions Tester")
        .createNotification(
            title,
            message,
            type
        ).notify(this)
}