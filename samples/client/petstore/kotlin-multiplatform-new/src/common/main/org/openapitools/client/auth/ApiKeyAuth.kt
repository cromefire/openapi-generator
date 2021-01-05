package org.openapitools.client.auth

import io.ktor.client.request.*

@Suppress("MemberVisibilityCanBePrivate")
public class ApiKeyAuth(public val location: Location, public val paramName: String) : Authentication {
    public var apiKey: String? = null
    public var apiKeyPrefix: String? = null

    override val isConfigured: Boolean
        get() = apiKey != null

    override fun configure(builder: HttpRequestBuilder) {
            val key: String = apiKey ?: throw IllegalStateException("ApiKeyAuth not configured")
            val prefix: String? = apiKeyPrefix
            val value: String = if (prefix != null) "$prefix $key" else key
            when (location) {
                Location.Query -> builder.parameter(paramName, value)
                Location.Header -> builder.header(paramName, value)
            }
        }

    public enum class Location {
        Query, Header;
    }

    public interface Configurer {
        public fun key(value: String)
        public fun keyPrefix(value: String?)
    }
}
