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

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.utils.EmptyContent
import io.ktor.http.*
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

import org.openapitools.client.auth.*
import org.openapitools.client.infrastructure.*

@Suppress(
    "unused",
    "RemoveRedundantQualifierName",
    "DuplicatedCode",
)
public open class PetApi : ApiClientBase {
    public constructor(
        baseUrl: String = "http://petstore.swagger.io/v2",
        httpClientEngine: HttpClientEngine? = null,
        json: Json = Json {}
    ) : super(baseUrl, httpClientEngine, json)

    public constructor(baseUrl: String, client: HttpClient) : super(
        baseUrl,
        client
    )

    internal constructor(
        baseUrl: String,
        client: HttpClient,
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
    )

    /**
     * Add a new pet to the store
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public suspend fun addPet(
        body: Pet,
    ) {
        return this.client.request {
            this.method = HttpMethod.parse("POST")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet")
            }

            //addAuthentication("petstore_auth")

            // Body
            this.body = body
        }
    }

    /**
     * Deletes a pet
     * 
     * @param petId Pet id to delete 
     * @param apiKey  (optional)
     * @return void
     */
    public suspend fun deletePet(
        petId: kotlin.Long,
        apiKey: kotlin.String? = null,
    ) {
        return this.client.request {
            this.method = HttpMethod.parse("DELETE")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/{petId}".replace("{petId}", petId.toString()))
            }

            //addAuthentication("petstore_auth")

            // Headers
            header("api_key", apiKey)
        }
    }

    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * @param status Status values that need to be considered for filter 
     * @return kotlin.collections.List<Pet>
     */
    public suspend fun findPetsByStatus(
        status: kotlin.collections.List<kotlin.String>,
    ): kotlin.collections.List<Pet> {
        return this.client.request {
            this.method = HttpMethod.parse("GET")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/findByStatus")
            }

            //addAuthentication("petstore_auth")

            // Query parameters
            parameter("status", status.joinToString(separator = collectionDelimiter("csv")))
        }
    }

    /**
     * Finds Pets by tags
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     * @param tags Tags to filter by 
     * @return kotlin.collections.List<Pet>
     */
    public suspend fun findPetsByTags(
        tags: kotlin.collections.List<kotlin.String>,
    ): kotlin.collections.List<Pet> {
        return this.client.request {
            this.method = HttpMethod.parse("GET")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/findByTags")
            }

            //addAuthentication("petstore_auth")

            // Query parameters
            parameter("tags", tags.joinToString(separator = collectionDelimiter("csv")))
        }
    }

    /**
     * Find pet by ID
     * Returns a single pet
     * @param petId ID of pet to return 
     * @return Pet
     */
    public suspend fun getPetById(
        petId: kotlin.Long,
    ): Pet {
        return this.client.request {
            this.method = HttpMethod.parse("GET")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/{petId}".replace("{petId}", petId.toString()))
            }

            //addAuthentication("api_key")
        }
    }

    /**
     * Update an existing pet
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public suspend fun updatePet(
        body: Pet,
    ) {
        return this.client.request {
            this.method = HttpMethod.parse("PUT")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet")
            }

            //addAuthentication("petstore_auth")

            // Body
            this.body = body
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
    public suspend fun updatePetWithForm(
        petId: kotlin.Long,
        name: kotlin.String? = null,
        status: kotlin.String? = null,
    ) {
        return this.client.request {
            this.method = HttpMethod.parse("POST")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/{petId}".replace("{petId}", petId.toString()))
            }

            //addAuthentication("petstore_auth")
            // Formdata
            this.body = FormDataContent(
                ParametersBuilder(2).apply {
                    if (name != null) {
                        append("name", name)
                    }
                    if (status != null) {
                        append("status", status)
                    }
                }.build()
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
    public suspend fun uploadFile(
        petId: kotlin.Long,
        additionalMetadata: kotlin.String? = null,
        file: io.ktor.client.request.forms.InputProvider? = null,
    ): ApiResponse {
        return this.client.request {
            this.method = HttpMethod.parse("POST")
            url {
                this.takeFrom(this@PetApi.baseUrl)
                appendPath("/pet/{petId}/uploadImage".replace("{petId}", petId.toString()))
            }

            //addAuthentication("petstore_auth")
            // Formdata
            this.body = MultiPartFormDataContent(
                formData {
                    if (additionalMetadata != null) {
                        append(FormPart("additionalMetadata", additionalMetadata))
                    }
                    if (file != null) {
                        append(FormPart("file", file))
                    }
                }
            )
        }
    }
}
