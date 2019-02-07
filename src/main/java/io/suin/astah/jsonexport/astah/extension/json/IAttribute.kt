package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.IAttribute
import io.suin.astah.jsonexport.json.data.model.Multiplicity
import io.suin.astah.jsonexport.json.data.model.Type

fun IAttribute.type() = Type(this.type.className(), this.multiplicity())

fun IAttribute.multiplicity(): Multiplicity =
        if (this.multiplicity.isEmpty()) Multiplicity(1, 1)
        else Multiplicity(
            this.multiplicity[0].upper,
            this.multiplicity[0].upper
        )

// IElement.isReadOnlyはUMLの{readOnly}ではなくAstahのモデルが読めるかどうかという意味なので
fun IAttribute.isReadonly() = !this.isChangeable
