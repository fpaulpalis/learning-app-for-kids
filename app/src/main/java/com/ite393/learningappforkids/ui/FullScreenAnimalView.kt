package com.ite393.learningappforkids.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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

@Composable
fun FullScreenAnimalView(animal: Animal, navController: NavController) {
    val soundPlayer = remember { SoundPlayer(navController.context) }

    // Start playing the sound when the screen loads
    LaunchedEffect(Unit) {
        soundPlayer.playLoopingSound(animal.sound)
    }

    Scaffold { paddingValues ->
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
                Text(
                    text = animal.name.uppercase(),
                    fontSize = 24.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}