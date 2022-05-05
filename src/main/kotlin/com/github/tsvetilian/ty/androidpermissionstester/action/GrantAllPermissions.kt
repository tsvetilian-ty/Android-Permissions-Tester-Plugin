package com.github.tsvetilian.ty.androidpermissionstester.action

import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class GrantAllPermissions(
    private val enable: Boolean,
    private val onClick: (() -> Unit)? = null
) :
    AbstractAction(Language.GRANT_ALL_LABEL) {

    override fun actionPerformed(e: ActionEvent?) {
        onClick?.invoke()
    }

    override fun isEnabled(): Boolean = enable
}