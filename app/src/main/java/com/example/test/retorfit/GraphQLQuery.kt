package com.example.test.retorfit

data class GraphQLQuery(
    val operationName: String,
    val query: String,
    val variables: Map<String, Any>
)