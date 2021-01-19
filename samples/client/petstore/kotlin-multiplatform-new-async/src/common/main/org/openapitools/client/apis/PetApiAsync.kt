/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
@file:Suppress("UnusedImport")

package org.openapitools.client.apis

import org.openapitools.client.models.ApiResponse
import org.openapitools.client.models.Pet

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.serializer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json

import org.openapitools.client.auth.*
import org.openapitools.client.infrastructure.*

public open class PetApiAsync : ApiClientBase {
    public val coroutineScope: CoroutineScope
    protected val coroutineClient: PetApi

    public constructor(
        baseUrl: String = "http://petstore.swagger.io/v2",
        httpClientEngine: HttpClientEngine? = null,
        json: Json = Json {},
        coroutineScope: CoroutineScope = GlobalScope,
    ) : super(baseUrl, httpClientEngine, json) {
        this.coroutineScope = coroutineScope
        this.coroutineClient = PetApi(baseUrl, httpClientEngine, json)
    }

    public constructor(
        baseUrl: String,
        client: HttpClient,
        coroutineScope: CoroutineScope,
    ) : super(
        baseUrl,
        client,
    ) {
        this.coroutineScope = coroutineScope
        this.coroutineClient = PetApi(baseUrl, client)
    }

    internal constructor(
        baseUrl: String,
        client: HttpClient,
        coroutineScope: CoroutineScope,
        apiKeyAuth: Map<String, ApiKeyAuth>,
        basicAuth: Map<String, HttpBasicAuth>,
        bearerAuth: Map<String, HttpBearerAuth>,
        oAuth: Map<String, OAuth>,
    ) : super(
        baseUrl,
        client,
        apiKeyAuth,
        basicAuth,
        bearerAuth,
        oAuth,
    ) {
        this.coroutineScope = coroutineScope
        this.coroutineClient = PetApi(baseUrl, client)
    }

    /**
     * Add a new pet to the store
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public fun addPetAsync(
        body: Pet,
    ): Deferred<Unit> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.addPet(
                body = body,
            )
        }
    }
    /**
     * Deletes a pet
     * 
     * @param petId Pet id to delete 
     * @param apiKey  (optional)
     * @return void
     */
    public fun deletePetAsync(
        petId: kotlin.Long,
        apiKey: kotlin.String? = null,
    ): Deferred<Unit> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.deletePet(
                petId = petId,
                apiKey = apiKey,
            )
        }
    }
    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * @param status Status values that need to be considered for filter 
     * @return kotlin.collections.List<Pet>
     */
    public fun findPetsByStatusAsync(
        status: kotlin.collections.List<kotlin.String>,
    ): Deferred<kotlin.collections.List<Pet>> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.findPetsByStatus(
                status = status,
            )
        }
    }
    /**
     * Finds Pets by tags
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     * @param tags Tags to filter by 
     * @return kotlin.collections.List<Pet>
     */
    public fun findPetsByTagsAsync(
        tags: kotlin.collections.List<kotlin.String>,
    ): Deferred<kotlin.collections.List<Pet>> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.findPetsByTags(
                tags = tags,
            )
        }
    }
    /**
     * Find pet by ID
     * Returns a single pet
     * @param petId ID of pet to return 
     * @return Pet
     */
    public fun getPetByIdAsync(
        petId: kotlin.Long,
    ): Deferred<Pet> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.getPetById(
                petId = petId,
            )
        }
    }
    /**
     * Update an existing pet
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public fun updatePetAsync(
        body: Pet,
    ): Deferred<Unit> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.updatePet(
                body = body,
            )
        }
    }
    /**
     * Updates a pet in the store with form data
     * 
     * @param petId ID of pet that needs to be updated 
     * @param name Updated name of the pet (optional)
     * @param status Updated status of the pet (optional)
     * @return void
     */
    public fun updatePetWithFormAsync(
        petId: kotlin.Long,
        name: kotlin.String? = null,
        status: kotlin.String? = null,
    ): Deferred<Unit> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.updatePetWithForm(
                petId = petId,
                name = name,
                status = status,
            )
        }
    }
    /**
     * uploads an image
     * 
     * @param petId ID of pet to update 
     * @param additionalMetadata Additional data to pass to server (optional)
     * @param file file to upload (optional)
     * @return ApiResponse
     */
    public fun uploadFileAsync(
        petId: kotlin.Long,
        additionalMetadata: kotlin.String? = null,
        file: io.ktor.client.request.forms.InputProvider? = null,
    ): Deferred<ApiResponse> {
        return coroutineScope.async {
            this@PetApiAsync.coroutineClient.uploadFile(
                petId = petId,
                additionalMetadata = additionalMetadata,
                file = file,
            )
        }
    }
}
