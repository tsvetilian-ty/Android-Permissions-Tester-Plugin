package com.github.tsvetilian.ty.androidpermissionstester.ui

import com.github.tsvetilian.ty.androidpermissionstester.core.PermissionActionType
import com.intellij.openapi.project.Project
import java.awt.Component
import javax.swing.DefaultCellEditor
import javax.swing.JCheckBox
import javax.swing.JTable

class PermissionActionEditor(
    val project: Project?,
    private val permissionType: PermissionActionType,
    private val enabled: Boolean = true,
    private val actionButtonListener: (permissionId: Int?) -> Unit
) :
    DefaultCellEditor(JCheckBox()) {

    private var action: PermissionActionType = permissionType

    private var panel: PermissionsActionsPanel = PermissionsActionsPanel(permissionType, enabled) {
        action = it
    }

    override fun getTableCellEditorComponent(
        table: JTable?,
        value: Any?,
        isSelected: Boolean,
        row: Int,
        column: Int
    ): Component {
        if(action != PermissionActionType.NONE) {
            actionButtonListener(row)
        }
        action = PermissionActionType.NONE
        return panel
    }

}