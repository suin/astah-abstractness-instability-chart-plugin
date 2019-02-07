package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.IClass
import com.change_vision.jude.api.inf.model.INamedElement
import com.change_vision.jude.api.inf.model.IPackage
import io.suin.astah.jsonexport.json.data.model.Class

fun IPackage.classes(): List<Class> =
        this.iClassesRecursively().map {
            Class(
                name = it.name,
                namespace = it.namespace(),
                isAbstract = it.isAbstract,
                isLeaf = it.isLeaf,
                generalizations = it.generalizations(),
                realizations = it.realizations(),
                properties = it.properties(),
                operations = it.operations()
            )
        }

private fun IPackage.iClassesRecursively() = getAllClasses(
    listOf(this)
)

private fun IPackage.iPackages() =
        this.ownedElements.filterIsInstance<IPackage>()

private fun IPackage.iClasses() =
        this.ownedElements.filterIsInstance<IClass>()

private tailrec fun getAllClasses(
    elements: List<INamedElement>,
    classes: List<IClass> = listOf()
): List<IClass> {
    val first = elements.firstOrNull()
    val rest = elements.drop(1)
    return when (first) {
        is IClass -> getAllClasses(
            rest,
            classes.plus(first)
        )
        is IPackage -> getAllClasses(
            rest + first.iPackages(), first.iClasses() + classes
        )
        else -> classes
    }
}