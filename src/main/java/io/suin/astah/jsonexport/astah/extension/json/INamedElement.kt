package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.INamedElement
import io.suin.astah.jsonexport.json.data.model.ClassName
import io.suin.astah.jsonexport.json.data.model.Visibility

fun INamedElement.realizations(): List<ClassName> =
        this.clientRealizations.map {
            ClassName(
                it.supplier.name,
                it.supplier.namespace()
            )
        }

fun INamedElement.visibility(): Visibility =
        when {
            this.isPublicVisibility -> Visibility.Public
            this.isPackageVisibility -> Visibility.Package
            this.isProtectedVisibility -> Visibility.Protected
            this.isPrivateVisibility -> Visibility.Private
            else -> Visibility.Public
        }