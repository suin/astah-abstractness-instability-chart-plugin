package io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension

import com.change_vision.jude.api.inf.model.IClass
import com.change_vision.jude.api.inf.model.INamedElement
import com.change_vision.jude.api.inf.model.IPackage

val IPackage.allClasses: List<IClass>
    get() = findClassesRecursively(listOf(this))

val IPackage.allPackages: List<IPackage>
    get() = findPackagesRecursively(listOf(this))

val IPackage.packages: List<IPackage>
    get() = ownedElements.filterIsInstance<IPackage>()

val IPackage.classes: List<IClass>
    get() = ownedElements.filterIsInstance<IClass>()

val IPackage.hasClasses: Boolean
    get() = this.classes.isNotEmpty()

val IPackage.abstractness: Double
    get() =
        if (!this.hasClasses) 0.0
        else this.abstractClassCount / this.classCount.toDouble()

val IPackage.abstractClassCount: Int
    get() = this.classes.count { it.isAbstract || it.isInterface }

val IPackage.classCount: Int
    get() = this.classes.size

val IPackage.instability: Double
    get() {
        val efferentCoupingCount = this.efferentCouplingCount
        val afferentCoupingCount = this.afferentCoupingCount
        val coupingTotal = afferentCoupingCount + efferentCoupingCount
        return if (coupingTotal == 0) 0.0
        else efferentCoupingCount / coupingTotal.toDouble()
    }

val IPackage.efferentCouplingCount: Int
    get() = this.classes.flatMap { it.efferentCoupledClasses }.distinct().size

val IPackage.afferentCoupingCount: Int
    get() = this.classes.flatMap { it.afferentCoupledClasses }.distinct().size


fun IPackage.findClassUseInOperations(targetClass: IClass): List<IClass> {
    val classes = mutableListOf<IClass>()
    for (klass in this.allClasses) {
        for (operation in klass.operations) {
            for (parameter in operation.parameters) {
                if (parameter.type === targetClass) {
                    classes += klass
                }
            }
            if (operation.returnType === targetClass) {
                classes += klass
            }
        }
    }
    return classes.distinct()
}

private tailrec fun findClassesRecursively(
    elements: List<INamedElement>,
    classes: List<IClass> = listOf()
): List<IClass> {
    val first = elements.firstOrNull()
    val rest = elements.drop(1)
    return when (first) {
        is IClass -> findClassesRecursively(rest, classes.plus(first))
        is IPackage -> findClassesRecursively(
            rest + first.packages,
            first.classes + classes
        )
        else -> classes
    }
}

private tailrec fun findPackagesRecursively(
    elements: List<INamedElement>,
    packages: List<IPackage> = listOf()
): List<IPackage> {
    val first = elements.firstOrNull()
    val rest = elements.drop(1)
    return when (first) {
        is IPackage -> findPackagesRecursively(
            first.packages + rest,
            packages.plus(first)
        )
        else -> packages
    }
}