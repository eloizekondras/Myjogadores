package com.example.myjogadores.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.myjogadores.data.Jogador
import com.example.myjogadores.ui.theme.views.JogadorDetailScreen
import com.example.myjogadores.ui.theme.views.JogadorScreen
import com.example.myjogadores.viewmodel.JogadoresViewModel
import com.google.gson.Gson
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun MyJogadoresApp() {
    MyJogadoresTheme {
        val navController = rememberNavController()
        val viewModel: JogadoresViewModel = viewModel()

        NavHost(
            navController = navController,
            startDestination = AppScreens.JogadoresList.name
        ) {
            composable(AppScreens.JogadoresList.name) {
                JogadorScreen(
                    jogadoresViewModel = viewModel,
                    onJogadorClick = { jogador ->
                        val jogadorJson = URLEncoder.encode(
                            Gson().toJson(jogador),
                            StandardCharsets.UTF_8.toString()
                        )
                        navController.navigate("${AppScreens.JogadorDetail.name}/$jogadorJson")
                    }
                )
            }
            composable(
                route = "${AppScreens.JogadorDetail.name}/{jogador}",
                arguments = listOf(navArgument("jogador") { type = NavType.StringType })
            ) { backStackEntry ->
                val jogadorJson = backStackEntry.arguments?.getString("jogador")
                val jogador = jogadorJson?.let {
                    Gson().fromJson(
                        URLDecoder.decode(it, StandardCharsets.UTF_8.toString()),
                        Jogador::class.java
                    )
                }

                jogador?.let {
                    JogadorDetailScreen(jogador = it) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}
