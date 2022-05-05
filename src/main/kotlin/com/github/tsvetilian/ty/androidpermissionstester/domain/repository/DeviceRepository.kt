package com.github.tsvetilian.ty.androidpermissionstester.domain.repository

import com.android.ddmlib.IDevice

interface DeviceRepository {
    fun getAllDevices(): List<IDevice>
    fun findDeviceByName(name: String): IDevice?
    fun grantPermission(packageName: String?, device: IDevice?, permission: String)
    fun revokePermission(packageName: String?, device: IDevice?, permission: String)
    fun grantAllPermissions(packageName: String?, device: IDevice?, permissionsList: MutableList<String>)
    fun revokeAllPermissions(packageName: String?, device: IDevice?, permissionsList: MutableList<String>)
}