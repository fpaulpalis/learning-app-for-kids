package com.ite393.learningappforkids.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ite393.learningappforkids.data.Animal
import com.ite393.learningappforkids.data.animals

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalGridScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Animal Sounds") }) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(animals) { animal ->
                    AnimalCard(animal, navController)
                }
            }
        }
    }
}

@Composable
fun AnimalCard(animal: Animal, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("fullScreen/${animal.name}") },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFCC80))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = animal.image),
                contentDescription = animal.name,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = animal.name, fontSize = 20.sp)
        }
    }
}
