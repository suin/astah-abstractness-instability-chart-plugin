package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.IOperation
import io.suin.astah.jsonexport.json.data.model.Multiplicity
import io.suin.astah.jsonexport.json.data.model.Parameter
import io.suin.astah.jsonexport.json.data.model.Type

fun IOperation.parameters(): List<Parameter> =
        this.parameters.map {
            Parameter(
                name = it.name,
                type = it.type()
            )
        }

fun IOperation.returnType(): Type? =
        if (this.returnType == null) null
        else Type(this.returnType.className(), Multiplicity(1, 1))