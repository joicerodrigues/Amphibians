/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.amphibians.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// TODO: Create a property for the base URL provided in the codelab
// TODO: Build the Moshi object with Kotlin adapter factory that Retrofit will be using to parse JSON
// TODO: Build a Retrofit object with the Moshi converter
private const val BASE_URL = //constante recebendo URL
    "https://developer.android.com/courses/pathways/android-basics-kotlin-unit-4-pathway-2/" // URL base do serviço da web
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build() // criando obj retrofit

    class AmphibianApiService{
        interface AmphibianApiService {
            // TODO: Declare a suspended function to get the list of amphibians
            @GET("android-basics-kotlin-unit-4-pathway-2-project-api.json") // informando que é uma solicitação GET
            suspend fun getAmphibians(): List<Amphibian> // recebe a string de resposta do serviço da Web.
        }

        // TODO: Create an object that provides a lazy-initialized retrofit service
        object AmphibianApi {
            val retrofitService: AmphibianApiService by lazy { // inicialização lenta
                retrofit.create(AmphibianApiService::class.java)
            }
        }
}

