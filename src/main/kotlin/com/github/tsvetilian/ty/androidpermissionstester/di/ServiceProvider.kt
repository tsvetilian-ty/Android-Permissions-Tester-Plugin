package com.github.tsvetilian.ty.androidpermissionstester.di

import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.DeviceRepository
import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.ManifestRepository
import com.github.tsvetilian.ty.androidpermissionstester.repository.DeviceRepositoryImpl
import com.github.tsvetilian.ty.androidpermissionstester.repository.ManifestRepositoryImpl
import com.intellij.openapi.project.Project

object ServiceProvider {

    fun getDeviceRepositoryProvider(project: Project?): DeviceRepository? {
        if(project == null) return null
        return DeviceRepositoryImpl.getInstance(project)
    }

    fun getPermissionsRepositoryProvider(project: Project?): ManifestRepository? {
        if(project == null) return null
        return ManifestRepositoryImpl(project)
    }

}