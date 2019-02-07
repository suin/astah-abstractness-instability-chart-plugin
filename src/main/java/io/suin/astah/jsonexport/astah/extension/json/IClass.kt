package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.IAttribute
import com.change_vision.jude.api.inf.model.IClass
import io.suin.astah.jsonexport.json.data.model.ClassName
import io.suin.astah.jsonexport.json.data.model.Operation
import io.suin.astah.jsonexport.json.data.model.Property

fun IClass.className(): ClassName = ClassName(this.name, this.namespace())

fun IClass.generalizations(): List<ClassName> =
        this.generalizations.map {
            ClassName(
                it.superType.name,
                it.superType.namespace()
            )
        }

fun IClass.properties(): List<Property> =
        this.navigableAttributes().map {
            Property(
                name = it.name,
                visibility = it.visibility(),
                isStatic = it.isStatic,
                isReadOnly = it.isReadonly(),
                initialValue = it.initialValue,
                type = it.type()
            )
        }

fun IClass.operations(): List<Operation> =
        this.operations.map {
            Operation(
                name = it.name,
                isAbstract = it.isAbstract,
                isLeaf = it.isLeaf,
                visibility = it.visibility(),
                isStatic = it.isStatic,
                parameters = it.parameters(),
                returnType = it.returnType()
            )
        }

private fun IClass.navigableAttributes(): List<IAttribute> =
        this.attributes.filter { it.navigability === "Navigable" }
