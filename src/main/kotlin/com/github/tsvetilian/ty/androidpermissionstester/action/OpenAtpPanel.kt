package com.github.tsvetilian.ty.androidpermissionstester.action

import com.github.tsvetilian.ty.androidpermissionstester.di.ServiceProvider
import com.github.tsvetilian.ty.androidpermissionstester.domain.contract.ActionClickedListener
import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.DeviceRepository
import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.ManifestRepository
import com.github.tsvetilian.ty.androidpermissionstester.ui.PermissionsControlPanel
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.util.containers.toArray

class OpenAtpPanel : AnAction(), ActionClickedListener {

    private var deviceRepository: DeviceRepository? = null
    private var manifestRepository: ManifestRepository? = null

    override fun actionPerformed(e: AnActionEvent) {
        deviceRepository = ServiceProvider.getDeviceRepositoryProvider(e.project)
        manifestRepository = ServiceProvider.getPermissionsRepositoryProvider(e.project)

        val dialog = PermissionsControlPanel(
            e.project,
            manifestRepository?.getPermissions() ?: mutableListOf(),
            deviceRepository?.getAllDevices()?.map { it.name }?.toArray(arrayOf()) ?: arrayOf(),
            this
        )
        dialog.show()
    }

    override fun onGrantClicked(device: String, permission: String) {
        deviceRepository?.grantPermission(
            manifestRepository?.getPackageName(),
            deviceRepository?.findDeviceByName(device),
            permission
        )
    }

    override fun onRevokeClicked(device: String, permission: String) {
        deviceRepository?.revokePermission(
            manifestRepository?.getPackageName(),
            deviceRepository?.findDeviceByName(device),
            permission
        )
    }

    override fun onGrantAllClicked(device: String, permissionsList: MutableList<String>) {
        deviceRepository?.grantAllPermissions(
            manifestRepository?.getPackageName(),
            deviceRepository?.findDeviceByName(device),
            permissionsList
        )
    }

    override fun onRevokeAllClicked(device: String, permissionsList: MutableList<String>) {
        deviceRepository?.revokeAllPermissions(
            manifestRepository?.getPackageName(),
            deviceRepository?.findDeviceByName(device),
            permissionsList
        )
    }
}