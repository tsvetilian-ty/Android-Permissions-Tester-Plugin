package com.github.tsvetilian.ty.androidpermissionstester.ui.helper

import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import com.github.tsvetilian.ty.androidpermissionstester.core.TableConstants
import javax.swing.table.AbstractTableModel

class PermissionsTableModel(private val permissions: List<String>) : AbstractTableModel() {
    private val columns =
        listOf(Language.PERMISSION_COLUMN_LABEL, Language.GRANT_COLUMN_LABEL, Language.REVOKE_COLUMN_LABEL)

    override fun getRowCount(): Int = permissions.size

    override fun getColumnCount(): Int = columns.size

    override fun getColumnName(columnIndex: Int): String = columns[columnIndex]

    override fun getColumnClass(columnIndex: Int): Class<*> {
        return if (columnIndex == TableConstants.PERMISSION_NAME_INDEX) {
            String::class.java
        } else {
            Object::class.java
        }
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean {
        return columnIndex != TableConstants.PERMISSION_NAME_INDEX
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        val value: Any? = null
        return if (columnIndex == TableConstants.PERMISSION_NAME_INDEX) {
            permissions[rowIndex]
        } else {
            value
        }
    }

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        if (columnIndex != TableConstants.PERMISSION_NAME_INDEX) {
            fireTableCellUpdated(rowIndex, columnIndex)
        }
    }
}