package com.github.tsvetilian.ty.androidpermissionstester.domain.contract

interface ActionClickedListener {
    fun onGrantClicked(device: String, permission: String)
    fun onRevokeClicked(device: String, permission: String)
    fun onGrantAllClicked(device: String, permissionsList: MutableList<String>)
    fun onRevokeAllClicked(device: String, permissionsList: MutableList<String>)
}