package com.github.tsvetilian.ty.androidpermissionstester.core

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.project.Project

fun Project?.notify(title: String, message: String) {
    NotificationGroup
        .create("Permissions Tester Notifications", NotificationDisplayType.BALLOON, false, "apt-w-id", null, "", null)
        .createNotification(title, message).notify(this)
}