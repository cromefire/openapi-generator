package org.openapitools.client.auth

import org.openapitools.client.infrastructure.Queries

class OAuth : Authentication {
    var accessToken: String? = null

    override fun apply(queries: Queries, headers: MutableMap<String, String?>) {
        val token: String = accessToken ?: return
        headers["Authorization"] = "Bearer $token"
    }
}