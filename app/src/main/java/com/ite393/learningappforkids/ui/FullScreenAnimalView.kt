package com.ite393.learningappforkids.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ite393.learningappforkids.data.Animal
import com.ite393.learningappforkids.util.SoundPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullScreenAnimalView(animal: Animal, navController: NavController) {
    val soundPlayer = remember { SoundPlayer(navController.context) }
    var isPlaying by remember { mutableStateOf(true) }

    // Play sound on launch, stop when exiting
    LaunchedEffect(Unit) {
        soundPlayer.playLoopingSound(animal.sound)
    }

    DisposableEffect(Unit) {
        onDispose {
            soundPlayer.stopSound()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(animal.name.uppercase(), fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel), contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFEB3B)
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = animal.image),
                    contentDescription = animal.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = animal.name.uppercase(),
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
                Button(onClick = {
                    if (isPlaying) {
                        soundPlayer.stopSound()
                    } else {
                        soundPlayer.playLoopingSound(animal.sound)
                    }
                    isPlaying = !isPlaying
                }) {
                    Text(if (isPlaying) "Stop Sound" else "Play Sound")
                }
            }
        }
    }
}
