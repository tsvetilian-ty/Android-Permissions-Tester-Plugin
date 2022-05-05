package com.github.tsvetilian.ty.androidpermissionstester.ui

import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import com.github.tsvetilian.ty.androidpermissionstester.core.PermissionActionType
import com.github.tsvetilian.ty.androidpermissionstester.ui.helper.PermissionTesterIcons
import java.awt.GridBagLayout
import javax.swing.JButton
import javax.swing.JPanel

class PermissionsActionsPanel(
    private val permissionType: PermissionActionType,
    private val enabled: Boolean = true,
    private val actionCb: ((permissionType: PermissionActionType) -> Unit)? = null
) :
    JPanel() {
    init {
        layout = GridBagLayout()
        isOpaque = false

        val permissionBtn = JButton().apply {
            isOpaque = false
            when (permissionType) {
                PermissionActionType.GRANT -> {
                    icon = PermissionTesterIcons.GrantPermission
                    this@PermissionsActionsPanel.toolTipText = Language.TOOLTIP_GRANT
                }
                PermissionActionType.REVOKE -> {
                    icon = PermissionTesterIcons.RevokePermission
                    this@PermissionsActionsPanel.toolTipText = Language.TOOLTIP_REVOKE
                }
            }
            addActionListener {
                actionCb?.invoke(permissionType)
            }
            isEnabled = enabled
        }

        add(permissionBtn)
    }
}