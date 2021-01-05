package org.openapitools.client.infrastructure

import io.ktor.http.*

internal fun URLBuilder.appendPath(path: String) {
    val ep = path.trimStart('/').split('/').joinToString("/") { it.encodeURLQueryComponent() }
    encodedPath = "${encodedPath.trimEnd('/')}/${ep}"
}

internal fun collectionDelimiter(collectionFormat: String) = when(collectionFormat) {
    "csv" -> ","
    "tsv" -> "\t"
    "pipe" -> "|"
    "space" -> " "
    else -> ""
}
