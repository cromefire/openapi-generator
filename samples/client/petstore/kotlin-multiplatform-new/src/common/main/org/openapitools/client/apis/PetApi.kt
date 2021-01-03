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

import org.openapitools.client.infrastructure.*
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.forms.FormPart
import io.ktor.client.utils.EmptyContent
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

@Suppress(
    "unused",
    "RemoveRedundantQualifierName",
    "RemoveExplicitTypeArguments",
    "RemoveRedundantCallsOfConversionMethods",
    "DuplicatedCode",
    "UnnecessaryVariable",
)
public open class PetApi : ApiClientBase {
    public constructor(
        baseUrl: String = "http://petstore.swagger.io/v2",
        httpClientEngine: HttpClientEngine? = null,
        json: Json = Json {}
    ) : super(baseUrl, httpClientEngine, json)

    internal constructor(baseUrl: String, client: HttpClient, serializer: KotlinxSerializer) : super(
        baseUrl,
        client,
        serializer
    )

    /**
     * Add a new pet to the store
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public suspend fun addPet(
        body: Pet,
    ): HttpResponse<Unit> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = body

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.POST,
            "/pet",
            queries = queriesOag,
            headers = headersOag
        )

        return jsonRequest(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
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
    ): HttpResponse<Unit> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
            "api_key" to this?.toString()
        )

        val configOag = RequestConfig(
            RequestMethod.DELETE,
            "/pet/{petId}".replace("{" + "petId" + "}", petId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Finds Pets by status
     * Multiple status values can be provided with comma separated strings
     * @param status Status values that need to be considered for filter 
     * @return kotlin.Array<Pet>
     */
    @Suppress("UNCHECKED_CAST")
    public suspend fun findPetsByStatus(
        status: kotlin.Array<kotlin.String>,
    ): HttpResponse<kotlin.Array<Pet>> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
            addMulti("status", status, "csv")
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.GET,
            "/pet/findByStatus",
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Finds Pets by tags
     * Multiple tags can be provided with comma separated strings. Use tag1, tag2, tag3 for testing.
     * @param tags Tags to filter by 
     * @return kotlin.Array<Pet>
     */
    @Suppress("UNCHECKED_CAST")
    public suspend fun findPetsByTags(
        tags: kotlin.Array<kotlin.String>,
    ): HttpResponse<kotlin.Array<Pet>> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
            addMulti("tags", tags, "csv")
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.GET,
            "/pet/findByTags",
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Find pet by ID
     * Returns a single pet
     * @param petId ID of pet to return 
     * @return Pet
     */
    @Suppress("UNCHECKED_CAST")
    public suspend fun getPetById(
        petId: kotlin.Long,
    ): HttpResponse<Pet> {
        val authNamesOag = listOf<String>("api_key")

        val bodyOag = 
            EmptyContent

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.GET,
            "/pet/{petId}".replace("{" + "petId" + "}", petId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return request(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * Update an existing pet
     * 
     * @param body Pet object that needs to be added to the store 
     * @return void
     */
    public suspend fun updatePet(
        body: Pet,
    ): HttpResponse<Unit> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = body

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.PUT,
            "/pet",
            queries = queriesOag,
            headers = headersOag
        )

        return jsonRequest(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
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
    ): HttpResponse<Unit> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = 
            ParametersBuilder().also {
                name?.apply { it.append("name", name.toString()) }
                status?.apply { it.append("status", status.toString()) }
            }.build()

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.POST,
            "/pet/{petId}".replace("{" + "petId" + "}", petId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return urlEncodedFormRequest(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
    /**
     * uploads an image
     * 
     * @param petId ID of pet to update 
     * @param additionalMetadata Additional data to pass to server (optional)
     * @param file file to upload (optional)
     * @return ApiResponse
     */
    @Suppress("UNCHECKED_CAST")
    public suspend fun uploadFile(
        petId: kotlin.Long,
        additionalMetadata: kotlin.String? = null,
        file: io.ktor.client.request.forms.InputProvider? = null,
    ): HttpResponse<ApiResponse> {
        val authNamesOag = listOf<String>("petstore_auth")

        val bodyOag = 
            formData {
                additionalMetadata?.let { append(FormPart("additionalMetadata", additionalMetadata)) }
                file?.let { append(FormPart("file", file)) }
            }

        val queriesOag = Queries {
        }

        val headersOag = mutableMapOf<String, String?>(
        )

        val configOag = RequestConfig(
            RequestMethod.POST,
            "/pet/{petId}/uploadImage".replace("{" + "petId" + "}", petId.toString()),
            queries = queriesOag,
            headers = headersOag
        )

        return multipartFormRequest(
            configOag,
            bodyOag,
            authNamesOag
        ).wrap()
    }
}
