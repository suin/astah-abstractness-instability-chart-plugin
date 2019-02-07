package io.suin.astah.jsonexport.json.data.model

data class Class(
    val name: String,
    val namespace: Namespace,
    val isAbstract: Boolean,
    val isLeaf: Boolean,
    val generalizations: List<ClassName>,
    val realizations: List<ClassName>,
    val properties: List<Property>,
    val operations: List<Operation>
)