package io.suin.astah.plugin.abstractnessinstabilitychart.astah.extension

import com.change_vision.jude.api.inf.model.IClass
import com.change_vision.jude.api.inf.model.IElement
import com.change_vision.jude.api.inf.model.IPackage

val IClass.isInterface: Boolean
    get() = this.stereotypes.contains("interface")

val IClass.namespaces: List<IPackage>
    get() {
        val namespace = mutableListOf<IPackage>()
        var owner: IElement? = this.owner
        while (owner is IPackage) {
            namespace += owner
            owner = owner.owner
        }
        return namespace.reversed()
    }

val IClass.modelRoot: IPackage
    get() = this.namespaces.first()

val IClass.afferentCoupledClasses: List<IClass>
    get() {
        val coupledClassesByAttributes: List<IClass> =
            this.attributes
                .filter { it.type is IClass }
                .filter { it.navigability != "Navigable" }
                .map { it.type }
        val coupledClassesByOperations: List<IClass> =
            this.modelRoot.findClassUseInOperations(this)
        val coupledClassesByRealizations: List<IClass> =
            this.supplierRealizations
                .map { it.client }
                .filterIsInstance<IClass>()
        val coupledClassesByGeneralizations: List<IClass> =
            this.specializations
                .filter { it.subType is IClass }
                .map { it.superType }
        val coupledClassesByDependencies: List<IClass> =
            this.supplierDependencies
                .map { it.client }
                .filterIsInstance<IClass>()
        val coupledClasses = (coupledClassesByAttributes +
                coupledClassesByOperations +
                coupledClassesByRealizations +
                coupledClassesByGeneralizations +
                coupledClassesByDependencies)
            .filterNot { it.isPrimitiveType }
            .filterNot { it.owner == this.owner }
            .distinct()
        return coupledClasses
    }

val IClass.efferentCoupledClasses: List<IClass>
    get() {
        val coupledClassesByAttributes: List<IClass> =
            this.attributes
                .filter { it.type is IClass }
                .filter { it.navigability == "Navigable" }
                .map { it.type }
        val coupledClassesByOperations: List<IClass> =
            this.operations
                .fold(listOf()) { classes, it ->
                    val parameterTypes =
                        it.parameters
                            .filter { it.type is IClass }
                            .map { it.type }
                    val returnTypes =
                        if (it.returnType is IClass) listOf(it.returnType)
                        else listOf()
                    classes + parameterTypes + returnTypes
                }
        val coupledClassesByRealizations: List<IClass> =
            this.clientRealizations
                .map { it.supplier }
                .filterIsInstance<IClass>()
        val coupledClassesByGeneralizations: List<IClass> =
            this.generalizations
                .filter { it.superType is IClass }
                .map { it.superType }
        val coupledClassesByDependencies: List<IClass> =
            this.clientDependencies
                .map { it.supplier }
                .filterIsInstance<IClass>()
        val coupledClasses = (coupledClassesByAttributes +
                coupledClassesByOperations +
                coupledClassesByRealizations +
                coupledClassesByGeneralizations +
                coupledClassesByDependencies)
            .filterNot { it.isPrimitiveType }
            .filterNot { it.owner == this.owner }
            .distinct()
        return coupledClasses
    }
