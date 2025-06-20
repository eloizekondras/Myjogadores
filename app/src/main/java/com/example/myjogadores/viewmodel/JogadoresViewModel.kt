package com.example.myjogadores.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjogadores.data.Jogador
import com.example.myjogadores.network.OpenJogadorApi
import com.example.myjogadores.state.JogadoresUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class JogadoresViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<JogadoresUiState>(JogadoresUiState.Loading)
    val uiState: StateFlow<JogadoresUiState> = _uiState.asStateFlow()

    init {
        getJogadores()
    }

    private fun getJogadores() {
        viewModelScope.launch {
            try {
                val jogadores = OpenJogadorApi.retrofitService.getJogadores()
                _uiState.value = JogadoresUiState.Success(jogadores)
                println("✅ Jogadores carregados: ${jogadores.size}")
            } catch (e: Exception) {
                println("❌ ERRO AO CARREGAR JOGADORES: ${e.message}")
                e.printStackTrace() // Mostra detalhes no logcat
                _uiState.value = JogadoresUiState.Error
            }
        }
    }

}
