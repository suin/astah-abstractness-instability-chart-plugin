package io.suin.astah.jsonexport

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.suin.astah.jsonexport.json.data.model.Class

class JacksonJsonEncoder: JsonEncoder {
    override fun encode(classes: List<Class>): String {
        val mapper = jacksonObjectMapper()
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(classes)
    }
}