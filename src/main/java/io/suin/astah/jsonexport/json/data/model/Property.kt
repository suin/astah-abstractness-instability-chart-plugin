package io.suin.astah.jsonexport.json.data.model

data class Property(
    val name: String,
    val visibility: Visibility,
    val isStatic: Boolean,
    val isReadOnly: Boolean,
    val initialValue: String,
    val type: Type
)