package io.suin.astah.jsonexport.astah.extension.json

import com.change_vision.jude.api.inf.model.IParameter
import io.suin.astah.jsonexport.json.data.model.Multiplicity
import io.suin.astah.jsonexport.json.data.model.Type

fun IParameter.type() = Type(this.type.className(), Multiplicity(1, 1))