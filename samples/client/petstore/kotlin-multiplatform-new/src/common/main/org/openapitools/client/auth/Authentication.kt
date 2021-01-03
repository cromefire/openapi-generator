package org.openapitools.client.auth

import org.openapitools.client.infrastructure.Queries

public interface Authentication {
    /**
     * Apply authentication settings to header and query params.
     *
     * @param queries Query parameters.
     * @param headers Header parameters.
     */
    public fun apply(queries: Queries, headers: MutableMap<String, String?>)
}
