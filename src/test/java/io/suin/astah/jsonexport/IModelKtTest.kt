package io.suin.astah.jsonexport

import org.junit.Test
import kotlin.test.assertEquals

class IModelKtTest {
    @Test
    fun getAllClasses() {
        val model = Model(
            Package(
                "a",
                Package(
                    "b",
                    Class("1"),
                    Class("2")
                )
            ),
            Package(
                "d",
                Class("3"),
                Class("4")
            ),
            Class("5"),
            Class("6")
        )
        assertEquals(
            listOf(
                Class("1"),
                Class("2"),
                Class("3"),
                Class("4"),
                Class("5"),
                Class("6")
            ),
            model.getAllClasses()
        )
    }
}