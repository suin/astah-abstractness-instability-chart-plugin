package io.suin.astah.jsonexport.json.data.model

data class Operation(
    val name: String,
    val isAbstract: Boolean,
    val isLeaf: Boolean,
    val visibility: Visibility,
    val isStatic: Boolean,
    val parameters: List<Parameter>,
    val returnType: Type?
)