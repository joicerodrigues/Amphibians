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
package com.example.amphibians.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amphibians.network.Amphibian
import com.example.amphibians.network.AmphibianApi
import kotlinx.coroutines.launch

enum class AmphibianApiStatus {LOADING, ERROR, DONE}

class AmphibianViewModel : ViewModel() {

    // TODO: Criar propriedades para representar MutableLiveData e LiveData para o status da API
    private val _status = MutableLiveData<AmphibianApiStatus>() // MutableLiveData interno que armazena o status da solicitação mais recente
    val status: LiveData<AmphibianApiStatus> = _status // LiveData externo imutável para o status da solicitação

    // TODO: Criar propriedades para representar MutableLiveData e LiveData para uma lista de objetos anfíbios
    private val _amphibian = MutableLiveData<Amphibian>()
    val amphibian: LiveData<Amphibian> = _amphibian


    // TODO: Crie propriedades para representar MutableLiveData e LiveData para um único objeto anfíbio.
    // Isso será usado para exibir os detalhes de um anfíbio quando um item da lista for clicado

    // TODO: Crie uma função que obtenha uma lista de anfíbios do serviço api e defina o
    // status através de uma Corrotina
    fun getAmphibianList(){
        viewModelScope.launch {
            _status.value = AmphibianApiStatus.LOADING
            try {
                _amphibian.value = AmphibianApi.retrofitService.getPhotos()
                _status.value = AmphibianApiStatus.DONE
            } catch (e: Exception) {
                _status.value = AmphibianApiStatus.ERROR
            }
        }
    }


    fun onAmphibianClicked(amphibian: Amphibian) {
        // TODO: Set the amphibian object
        this.amphibian
    }
}
