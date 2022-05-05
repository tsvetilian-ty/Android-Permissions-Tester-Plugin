package com.github.tsvetilian.ty.androidpermissionstester.ui

import com.github.tsvetilian.ty.androidpermissionstester.action.GrantAllPermissions
import com.github.tsvetilian.ty.androidpermissionstester.action.RevokeAllPermissions
import com.github.tsvetilian.ty.androidpermissionstester.core.DimensionContainer
import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import com.github.tsvetilian.ty.androidpermissionstester.core.PermissionActionType
import com.github.tsvetilian.ty.androidpermissionstester.core.TableConstants
import com.github.tsvetilian.ty.androidpermissionstester.domain.contract.ActionClickedListener
import com.github.tsvetilian.ty.androidpermissionstester.ui.helper.PermissionsTableModel
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.layout.GrowPolicy
import com.intellij.ui.layout.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import java.awt.Dimension
import javax.swing.*

class PermissionsControlPanel(
    private val project: Project?,
    private val permissionsList: MutableList<String>,
    private val devices: Array<String>,
    private val actionClickedListener: ActionClickedListener,
) : DialogWrapper(project) {

    private val noDeviceAvailable = arrayOf(Language.NO_DEVICE_AVAILABLE)

    private var selectedDevice: DefaultComboBoxModel<String> = DefaultComboBoxModel(
        if (devices.isNotEmpty()) {
            devices
        } else {
            noDeviceAvailable
        }
    )

    init {
        super.init()
    }

    private fun createPermissionsTable(permissions: MutableList<String>): JPanel {
        val table = JBTable(PermissionsTableModel(permissions)).apply {
            emptyText.text = Language.NO_PERMISSIONS_DETECTED
            border = JBUI.Borders.empty()
            autoResizeMode = JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS
            showHorizontalLines = false
            showVerticalLines = false
            columnSelectionAllowed = false
            rowSelectionAllowed = false
            dragEnabled = false

            columnModel.apply {
                getColumn(TableConstants.PERMISSION_NAME_INDEX).preferredWidth = 280
                getColumn(TableConstants.PERMISSION_NAME_INDEX).minWidth = 280
                getColumn(TableConstants.GRANT_INDEX).minWidth = 80
                getColumn(TableConstants.GRANT_INDEX).maxWidth = 80
                getColumn(TableConstants.REVOKE_INDEX).minWidth = 80
                getColumn(TableConstants.REVOKE_INDEX).maxWidth = 80

                getColumn(TableConstants.GRANT_INDEX).setCellRenderer { _, _, _, _, _, _ ->
                    PermissionsActionsPanel(
                        PermissionActionType.GRANT,
                        permissionsList.isNotEmpty() && devices.isNotEmpty()
                    )
                }

                getColumn(TableConstants.REVOKE_INDEX).setCellRenderer { _, _, _, _, _, _ ->
                    PermissionsActionsPanel(
                        PermissionActionType.REVOKE,
                        permissionsList.isNotEmpty() && devices.isNotEmpty()
                    )
                }

                getColumn(TableConstants.GRANT_INDEX).cellEditor =
                    PermissionActionEditor(
                        project,
                        PermissionActionType.GRANT,
                        permissionsList.isNotEmpty() && devices.isNotEmpty()
                    ) { permission ->
                        if (devices.isNotEmpty()) {
                            actionClickedListener.onGrantClicked(
                                selectedDevice.selectedItem as String,
                                permissions[permission!!]
                            )
                        }
                    }

                getColumn(TableConstants.REVOKE_INDEX).cellEditor =
                    PermissionActionEditor(
                        project,
                        PermissionActionType.REVOKE,
                        permissionsList.isNotEmpty() && devices.isNotEmpty()
                    ) { permission ->
                        if (devices.isNotEmpty()) {
                            actionClickedListener.onRevokeClicked(
                                selectedDevice.selectedItem as String,
                                permissions[permission!!]
                            )
                        }
                    }
            }
        }

        val decorator = ToolbarDecorator.createDecorator(table).apply {
            setMinimumSize(DimensionContainer.getTableDimensions())
            setPreferredSize(DimensionContainer.getTableDimensions())
        }

        return decorator.createPanel()
    }

    override fun isModal(): Boolean = true

    override fun createCenterPanel(): JComponent = panel {
        row {
            comboBox(selectedDevice, { return@comboBox "" }, {}).growPolicy(GrowPolicy.MEDIUM_TEXT)
                .enabled(devices.isNotEmpty())
        }
        row {
            component(createPermissionsTable(permissionsList))
        }
    }.apply {
        setResizable(false)
        minimumSize = DimensionContainer.getWindowDimensions()
        maximumSize = DimensionContainer.getWindowDimensions()
        preferredSize = DimensionContainer.getWindowDimensions()
        title = Language.TITLE
    }

    override fun getSize(): Dimension = DimensionContainer.getWindowDimensions()

    override fun getInitialSize(): Dimension = DimensionContainer.getWindowDimensions()

    override fun getPreferredSize(): Dimension = DimensionContainer.getWindowDimensions()

    override fun createActions(): Array<Action> {
        super.createActions()
        return arrayOf(cancelAction)
    }

    override fun createLeftSideActions(): Array<Action> {
        super.createLeftSideActions()
        return arrayOf(
            GrantAllPermissions(
                permissionsList.isNotEmpty() && devices.isNotEmpty(),
            ) {
                actionClickedListener.onGrantAllClicked(selectedDevice.selectedItem as String, permissionsList)
            },
            RevokeAllPermissions(
                permissionsList.isNotEmpty() && devices.isNotEmpty(),
            ) {
                actionClickedListener.onRevokeAllClicked(selectedDevice.selectedItem as String, permissionsList)
            }
        )
    }
}

