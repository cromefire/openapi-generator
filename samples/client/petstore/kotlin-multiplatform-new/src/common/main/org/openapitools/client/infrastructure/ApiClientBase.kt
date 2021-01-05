package org.openapitools.client.infrastructure

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent
import io.ktor.http.*
import io.ktor.http.content.PartData
import kotlinx.serialization.json.Json
import io.ktor.client.features.json.*

import org.openapitools.client.auth.*

public open class ApiClientBase {
    protected val baseUrl: String
    protected val client: HttpClient
    protected val authentications: Map<String, Authentication> by lazy {
        mapOf(
            "api_key" to ApiKeyAuth("header", "api_key"),
            "petstore_auth" to OAuth(),
        )
    }

    internal constructor(baseUrl: String, httpClientEngine: HttpClientEngine?, json: Json = Json {}) {
        this.baseUrl = baseUrl
        val serializer = KotlinxSerializer(json)
        val jsonConfig: JsonFeature.Config.() -> Unit = { this.serializer = serializer }
        val clientConfig: (HttpClientConfig<*>) -> Unit = { it.install(JsonFeature, jsonConfig) }
        client = if (httpClientEngine == null) {
            HttpClient(clientConfig)
        } else {
            HttpClient(httpClientEngine, clientConfig)
        }
    }

    internal constructor(baseUrl: String, client: HttpClient) {
        this.baseUrl = baseUrl
        this.client = client
    }


    public companion object {
        protected val UNSAFE_HEADERS: List<String> = listOf()
    }

    /**
     * Set the username for the first HTTP basic authentication.
     *
     * @param username Username
     */
    public fun setUsername(username: String) {
        val auth = authentications.values.firstOrNull { it is HttpBasicAuth } as HttpBasicAuth?
                ?: throw Exception("No HTTP basic authentication configured")
        auth.username = username
    }

    /**
     * Set the password for the first HTTP basic authentication.
     *
     * @param password Password
     */
    public fun setPassword(password: String) {
        val auth = authentications.values.firstOrNull { it is HttpBasicAuth } as HttpBasicAuth?
                ?: throw Exception("No HTTP basic authentication configured")
        auth.password = password
    }

    /**
     * Set the API key value for the first API key authentication.
     *
     * @param apiKey API key
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    public fun setApiKey(apiKey: String, paramName: String? = null) {
        val auth = authentications.values.firstOrNull { it is ApiKeyAuth && (paramName == null || paramName == it.paramName)} as ApiKeyAuth?
                ?: throw Exception("No API key authentication configured")
        auth.apiKey = apiKey
    }

    /**
     * Set the API key prefix for the first API key authentication.
     *
     * @param apiKeyPrefix API key prefix
     * @param paramName The name of the API key parameter, or null or set the first key.
     */
    public fun setApiKeyPrefix(apiKeyPrefix: String, paramName: String? = null) {
        val auth = authentications.values.firstOrNull { it is ApiKeyAuth && (paramName == null || paramName == it.paramName) } as ApiKeyAuth?
                ?: throw Exception("No API key authentication configured")
        auth.apiKeyPrefix = apiKeyPrefix
    }

    /**
     * Set the access token for the first OAuth2 authentication.
     *
     * @param accessToken Access token
     */
    public fun setAccessToken(accessToken: String) {
        val auth = authentications.values.firstOrNull { it is OAuth } as OAuth?
                ?: throw Exception("No OAuth2 authentication configured")
        auth.accessToken = accessToken
    }

    /**
     * Set the access token for the first Bearer authentication.
     *
     * @param bearerToken The bearer token.
     */
    public fun setBearerToken(bearerToken: String) {
        val auth = authentications.values.firstOrNull { it is HttpBearerAuth } as HttpBearerAuth?
                ?: throw Exception("No Bearer authentication configured")
        auth.bearerToken = bearerToken
    }

    private fun RequestConfig.updateForAuth(authNames: List<String>) {
        for (authName in authNames) {
            val auth = authentications[authName] ?: throw Exception("Authentication undefined: $authName")
            auth.apply(queries, headers)
        }
    }
}
