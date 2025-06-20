package com.example.myjogadores.ui.theme.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.draw.clip
import com.example.myjogadores.data.Jogador
import com.example.myjogadores.network.BASE_URL
import com.example.myjogadores.R
import com.example.myjogadores.viewmodel.JogadoresViewModel
import com.example.myjogadores.state.JogadoresUiState

@Composable
fun JogadorScreen(
    jogadoresViewModel: JogadoresViewModel = viewModel(),
    onJogadorClick: (Jogador) -> Unit
) {
    val uiState = jogadoresViewModel.uiState.collectAsState().value

    when (uiState) {
        is JogadoresUiState.Loading -> LoadingScreen()
        is JogadoresUiState.Success -> JogadoresList(uiState.jogadores, onJogadorClick)
        is JogadoresUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Erro ao carregar os dados.")
    }
}

@Composable
fun JogadoresList(jogadores: List<Jogador>, onJogadorClick: (Jogador) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(jogadores) { jogador ->
            JogadorEntry(jogador = jogador, onClick = { onJogadorClick(jogador) })
        }
    }
}


@Composable
fun JogadorEntry(jogador: Jogador, onClick: () -> Unit) {
    val shape = RoundedCornerShape(8.dp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },  // <-- clique aqui
        shape = shape,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(160.dp)) {
                AsyncImage(
                    model = BASE_URL + jogador.imagem,
                    placeholder = painterResource(R.drawable.capa),
                    contentDescription = "Foto do jogador ${jogador.nome}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape)
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(Color.Black.copy(alpha = 0.6f))
                        .fillMaxWidth()
                        .padding(4.dp),
                    text = jogador.nome,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = jogador.descricao,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
