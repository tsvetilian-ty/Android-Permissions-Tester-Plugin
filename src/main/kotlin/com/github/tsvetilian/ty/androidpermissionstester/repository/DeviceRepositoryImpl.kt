package com.github.tsvetilian.ty.androidpermissionstester.repository

import com.android.ddmlib.IDevice
import com.android.ddmlib.NullOutputReceiver
import com.github.tsvetilian.ty.androidpermissionstester.core.Commands
import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import com.github.tsvetilian.ty.androidpermissionstester.core.notify
import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.DeviceRepository
import com.intellij.openapi.project.Project
import org.jetbrains.android.sdk.AndroidSdkUtils

class DeviceRepositoryImpl(
    private val project: Project
) : DeviceRepository {

    override fun getAllDevices(): List<IDevice> {
        val resultDevices: MutableList<IDevice> = mutableListOf()
        val availableDevices = AndroidSdkUtils.getDebugBridge(project)?.devices
        availableDevices?.let {
            resultDevices.addAll(availableDevices.toList())
        }
        return resultDevices
    }

    override fun findDeviceByName(name: String): IDevice? = getAllDevices().find { it.name == name }

    override fun grantPermission(packageName: String?, device: IDevice?, permission: String) {
        try {
            device?.executeShellCommand(Commands.grant(packageName, permission), NullOutputReceiver())
        } catch (error: Throwable) {
            project.notify(
                Language.NOTIFICATION_TITLE,
                Language.getUnableToGrandPermissionMessage(permission, packageName)
            )
            return
        }

        project.notify(Language.NOTIFICATION_TITLE, Language.getPermissionGrantedMessage(permission, packageName))
    }

    override fun revokePermission(packageName: String?, device: IDevice?, permission: String) {
        try {
            device?.executeShellCommand(Commands.revoke(packageName, permission), NullOutputReceiver())
        } catch (error: Throwable) {
            project.notify(
                Language.NOTIFICATION_TITLE,
                Language.getUnableToRevokePermissionMessage(permission, packageName)
            )
            return
        }

        project.notify(Language.NOTIFICATION_TITLE, Language.getPermissionRevokeMessage(permission, packageName))
    }

    override fun grantAllPermissions(packageName: String?, device: IDevice?, permissionsList: MutableList<String>) {
        for (permission in permissionsList) {
            try {
                device?.executeShellCommand(Commands.grant(packageName, permission), NullOutputReceiver())
            } catch (error: Throwable) {
                project.notify(
                    Language.NOTIFICATION_TITLE,
                    Language.getUnableToGrandAllPermissionsMessage(packageName)
                )
                return
            }
        }

        project.notify(Language.NOTIFICATION_TITLE, Language.ALL_PERMISSIONS_GRANTED_MESSAGE)
    }

    override fun revokeAllPermissions(packageName: String?, device: IDevice?, permissionsList: MutableList<String>) {
        for (permission in permissionsList) {
            try {
                device?.executeShellCommand(Commands.revoke(packageName, permission), NullOutputReceiver())
            } catch (error: Throwable) {
                project.notify(
                    Language.NOTIFICATION_TITLE,
                    Language.getUnableToRevokeAllPermissionsMessage(packageName)
                )
                return
            }
        }

        project.notify(Language.NOTIFICATION_TITLE, Language.ALL_PERMISSIONS_REVOKED_MESSAGE)
    }

    companion object {
        fun getInstance(project: Project): DeviceRepository {
            return DeviceRepositoryImpl(project)
        }
    }
}