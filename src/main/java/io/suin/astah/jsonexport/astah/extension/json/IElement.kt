package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.*

fun IElement.namespace(): List<String> {
    val namespace = mutableListOf<String>()
    var owner: IElement? = this.owner
    while (owner is IPackage && owner.isPackage()) {
        namespace += owner.name
        owner = owner.owner
    }
    return namespace.reversed()
}

fun IElement.isPackage() =
        this is IPackage
        && this !is IModel
        && this !is IERModel
        && this !is ISubsystem