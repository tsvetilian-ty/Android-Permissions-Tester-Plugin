package com.github.tsvetilian.ty.androidpermissionstester.repository

import com.github.tsvetilian.ty.androidpermissionstester.domain.repository.ManifestRepository
import com.intellij.openapi.project.Project
import org.jetbrains.android.dom.manifest.Manifest
import org.jetbrains.android.facet.AndroidFacet
import org.jetbrains.android.facet.AndroidRootUtil
import org.jetbrains.android.util.AndroidUtils
import org.jetbrains.kotlin.idea.util.projectStructure.allModules

class ManifestRepositoryImpl(
    private val project: Project
) : ManifestRepository {

    override fun getPackageName(): String? {
        try {
            for (module in project.allModules()) {
                val androidFacet = AndroidFacet.getInstance(module) ?: continue
                val manifestFile = AndroidRootUtil.getManifestFileForCompiler(androidFacet) ?: continue
                val manifest =
                    AndroidUtils.loadDomElement(androidFacet.module, manifestFile, Manifest::class.java) ?: continue
                return manifest.`package`.toString()
            }
        } catch (error: Throwable) {
            return null
        }

        return null
    }

    override fun getPermissions(): MutableList<String> {
        val result = mutableSetOf<String>()

        try {
            for (module in project.allModules()) {
                val androidFacet = AndroidFacet.getInstance(module) ?: continue
                val manifestFile = AndroidRootUtil.getManifestFileForCompiler(androidFacet) ?: continue
                val manifest =
                    AndroidUtils.loadDomElement(androidFacet.module, manifestFile, Manifest::class.java) ?: continue
                result.addAll(manifest.usesPermissions.map { per -> per.name.toString() })
            }
        } catch (error: Throwable) {
            return result.toMutableList()
        }

        return result.toMutableList()
    }

}