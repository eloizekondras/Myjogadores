package com.example.myjogadores.ui.theme.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myjogadores.data.Jogador
import com.example.myjogadores.network.BASE_URL

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JogadorDetailScreen(
    jogador: Jogador,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(jogador.nome) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = BASE_URL + jogador.imagem,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Descrição: ${jogador.descricao}")
            Text("Posição: ${jogador.posicao}")
            Text("Nacionalidade: ${jogador.nacionalidade}")
            Text("Idade: ${jogador.idade}")
            Text("Clube: ${jogador.clube}")
            Text("Número: ${jogador.numero}")
            Text("Altura: ${jogador.altura}")
            Text("Peso: ${jogador.peso}")
            Text("Gols: ${jogador.gols}")
            Text("Títulos: ${jogador.titulos}")
        }
    }
}
