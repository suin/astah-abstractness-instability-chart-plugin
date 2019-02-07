package io.suin.astah.jsonexport

import io.suin.astah.jsonexport.json.data.model.Class

interface JsonEncoder {
    fun encode(classes: List<Class>): String
}