package io.suin.astah.jsonexport

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class Hello {
    fun json() {
        val mapper = jacksonObjectMapper()

        println("=== Kotlin Object to JSON ===")
        val person = Person(
                "Kolineer Master",
                30,
                listOf("I am Kotlin Master", "still learning Kotlin")
        )

        println("1- String")
        var jsonStr = mapper.writeValueAsString(person)
        println(jsonStr)

        println("2- Formatted String")
        jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                person
        )
        println(jsonStr)

        println("3- File -> manually check file for result")
    }
}

