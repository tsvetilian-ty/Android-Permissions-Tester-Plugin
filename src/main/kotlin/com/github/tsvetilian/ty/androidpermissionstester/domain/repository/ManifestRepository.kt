package com.github.tsvetilian.ty.androidpermissionstester.domain.repository

interface ManifestRepository {
    fun getPackageName(): String?
    fun getPermissions(): MutableList<String>
}