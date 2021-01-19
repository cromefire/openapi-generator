package org.openapitools.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.serialization.json.Json

import org.openapitools.client.apis.*
import org.openapitools.client.auth.*
import org.openapitools.client.infrastructure.ApiClientBase

@Suppress("RemoveRedundantBackticks", "MemberVisibilityCanBePrivate", "unused")
public open class ApiClientAsync(
    baseUrl: String = "http://petstore.swagger.io/v2",
    client: HttpClient,
    coroutineScope: CoroutineScope = GlobalScope,
) {
    public constructor(baseUrl: String, httpClientEngine: HttpClientEngine? = null, json: Json = Json {}, coroutineScope: CoroutineScope = GlobalScope) :
        this(baseUrl, createHttpClient(httpClientEngine, KotlinxSerializer(json)), coroutineScope)

    protected val apiKeyAuth: Map<String, ApiKeyAuth> = mapOf(
        "api_key" to ApiKeyAuth(ApiKeyAuth.Location.Header, "api_key"),
    )
    protected val basicAuth: Map<String, HttpBasicAuth> = mapOf(
    )
    protected val bearerAuth: Map<String, HttpBearerAuth> = mapOf(
    )
    protected val oAuth: Map<String, OAuth> = mapOf(
        "petstore_auth" to OAuth(),
    )

    public val `pet`: PetApiAsync by lazy {
        PetApiAsync(baseUrl, client, coroutineScope, apiKeyAuth, basicAuth, bearerAuth, oAuth)
    }
    public val `store`: StoreApiAsync by lazy {
        StoreApiAsync(baseUrl, client, coroutineScope, apiKeyAuth, basicAuth, bearerAuth, oAuth)
    }
    public val `user`: UserApiAsync by lazy {
        UserApiAsync(baseUrl, client, coroutineScope, apiKeyAuth, basicAuth, bearerAuth, oAuth)
    }

    public val allClients: Set<ApiClientBase> by lazy {
        setOf(
            `pet`,
            `store`,
            `user`,
        )
    }

    /**
     * Allows configuring of a [ApiKeyAuth]. Please look at the documentation to find out which are available.
     */
    public fun configureApiKey(authName: String, apiKey: String, apiKeyPrefix: String? = null) {
        val auth = apiKeyAuth[authName] ?: throw IllegalArgumentException("Cannot find ApiKeyAuth named \"$authName\"")
        auth.apiKey = apiKey
        auth.apiKeyPrefix = apiKeyPrefix
    }

    /**
     * Allows configuring of a [ApiKeyAuth] via lambda.  Please look at the documentation to find out which are
     * available.
     *
     * It is required that you specify the API key via `key()`.
     *
     * Example:
     * ```kotlin
     * api.configureApiKey("your-authentication") {
     *     key("your-api-key")
     *     keyPrefix("YourPrefix ") // Optional
     * }
     * ```
     *
     * @param authName The name of the authentication to be configured.
     * @exception IllegalArgumentException Thrown when the [ApiKeyAuth] was not found or no API key was given.
     */
    public fun configureApiKey(authName: String, block: ApiKeyAuth.Configurer.() -> Unit) {
        val auth = apiKeyAuth[authName] ?: throw IllegalArgumentException("Cannot find ApiKeyAuth named \"$authName\"")
        val configurer = ApiKeyConfigurer()
        block(configurer)
        val key = configurer.key ?: throw IllegalArgumentException("Specifying \"key\" is required")
        auth.apiKey = key
        auth.apiKeyPrefix = configurer.keyPrefix
    }

    /**
     * Allows configuring of a [HttpBearerAuth]. Please look at the documentation to find out which are available.
     */
    public fun configureBearer(authName: String, token: String) {
        val auth =
            bearerAuth[authName] ?: throw IllegalArgumentException("Cannot find HttpBearerAuth named \"$authName\"")
        auth.bearerToken = token
    }

    /**
     * Allows configuring of a [HttpBearerAuth] via lambda. Please look at the documentation to find out which are
     * available.
     *
     * It is required that you specify the bearer token via `token()`.
     *
     * Example:
     * ```kotlin
     * api.configureBearer("your-authentication") {
     *     token("your-token")
     * }
     * ```
     *
     * @param authName The name of the authentication to be configured.
     * @exception IllegalArgumentException Thrown when the [HttpBasicAuth] was not found or no bearer token was given.
     */
    public fun configureBearer(authName: String, block: HttpBearerAuth.Configurer.() -> Unit) {
        val auth =
            bearerAuth[authName] ?: throw IllegalArgumentException("Cannot find HttpBearerAuth named \"$authName\"")
        val configurer = BearerConfigurer()
        block(configurer)
        val token = configurer.token ?: throw IllegalArgumentException("Specifying \"token\" is required")
        auth.bearerToken = token
    }

    /**
     * Allows configuring of a [HttpBasicAuth]. Please look at the documentation to find out which are available.
     * At the minimum either [username] or [password] must be specified.
     *
     * @exception IllegalArgumentException Thrown if neither [username] nor [password] is specified.
     */
    public fun configureBasic(authName: String, username: String?, password: String?) {
        val auth =
            basicAuth[authName] ?: throw IllegalArgumentException("Cannot find HttpBasicAuth named \"$authName\"")
        if (username == null && password == null) {
            throw IllegalArgumentException("One of \"username\" or \"password\" is required")
        }
        auth.username = username
        auth.password = password
    }

    /**
     * Allows configuring of a [HttpBasicAuth] via lambda.  Please look at the documentation to find out which are
     * available.
     *
     * At the minimum either `username()` or `password()` must be called.
     *
     * Example:
     * ```kotlin
     * api.configureBasic("your-authentication") {
     *     // At minimum one has to be specified
     *     username("your-username")
     *     password("your-password")
     * }
     * ```
     *
     * @param authName The name of the authentication to be configured.
     * @exception IllegalArgumentException Thrown when the [HttpBasicAuth] was not found or neither username nor password
     * was given.
     */
    public fun configureBasic(authName: String, block: HttpBasicAuth.Configurer.() -> Unit) {
        val auth =
            basicAuth[authName] ?: throw IllegalArgumentException("Cannot find HttpBasicAuth named \"$authName\"")
        val configurer = BasicConfigurer()
        block(configurer)
        val username = configurer.username
        val password = configurer.password
        if (username == null && password == null) {
            throw IllegalArgumentException("One of \"username\" or \"password\" is required")
        }
        auth.username = username
        auth.password = password
    }

    /**
     * Allows configuring of a [OAuth]. Please look at the documentation to find out which are available.
     */
    public fun configureOAuth(authName: String, token: String) {
        val auth = oAuth[authName] ?: throw IllegalArgumentException("Cannot find OAuth named \"$authName\"")
        auth.accessToken = token
    }

    /**
     * Allows configuring of a [OAuth] via lambda.  Please look at the documentation to find out which are available.
     *
     * It is required that you specify the OAuth token via `token()`.
     *
     * Example:
     * ```kotlin
     * api.configureOAuth("your-authentication") {
     *     token("your-OAuth-token")
     * }
     * ```
     *
     * @param authName The name of the authentication to be configured.
     * @exception IllegalArgumentException Thrown when the [OAuth] was not found or no OAuth token was given.
     */
    public fun configureOAuth(authName: String, block: OAuth.Configurer.() -> Unit) {
        val auth = oAuth[authName] ?: throw IllegalArgumentException("Cannot find OAuth named \"$authName\"")
        val configurer = OAuthConfigurer()
        block(configurer)
        val token = configurer.token ?: throw IllegalArgumentException("Specifying \"token\" is required")
        auth.accessToken = token
    }

    private data class ApiKeyConfigurer(var key: String? = null, var keyPrefix: String? = null) :
        ApiKeyAuth.Configurer {
        override fun key(value: String) {
            key = value
        }

        override fun keyPrefix(value: String?) {
            keyPrefix = value
        }
    }

    private data class BearerConfigurer(var token: String? = null) :
        HttpBearerAuth.Configurer {
        override fun token(value: String) {
            token = value
        }
    }

    private data class BasicConfigurer(var username: String? = null, var password: String? = null) :
        HttpBasicAuth.Configurer {
        override fun username(value: String?) {
            username = value
        }

        override fun password(value: String?) {
            password = value
        }
    }

    private data class OAuthConfigurer(var token: String? = null) :
        OAuth.Configurer {
        override fun token(value: String) {
            token = value
        }
    }
}
