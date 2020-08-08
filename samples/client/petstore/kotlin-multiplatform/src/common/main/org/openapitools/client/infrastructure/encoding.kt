package org.openapitools.client.infrastructure

import kotlinx.io.core.toByteArray

fun String.toBase64() = toByteArray().encodeBase64()
