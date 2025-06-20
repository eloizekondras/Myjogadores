package com.example.myjogadores.state

import com.example.myjogadores.data.Jogador

sealed class JogadoresUiState {
    object Loading : JogadoresUiState()
    data class Success(val jogadores: List<Jogador>) : JogadoresUiState()
    object Error : JogadoresUiState()
}
