package com.github.tsvetilian.ty.androidpermissionstester.action

import com.github.tsvetilian.ty.androidpermissionstester.core.Language
import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class RevokeAllPermissions(
    private val enable: Boolean,
    private val onClick: (() -> Unit)? = null
) :
    AbstractAction(Language.REVOKE_ALL_LABEL) {

    override fun actionPerformed(e: ActionEvent?) {
        onClick?.invoke()
    }

    override fun isEnabled(): Boolean = enable
}