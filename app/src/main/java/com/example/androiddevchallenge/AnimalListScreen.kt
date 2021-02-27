/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.AnimalRepository
import com.example.androiddevchallenge.model.Id
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AnimalListScreen(animalRepository: AnimalRepository, navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Animal List") }) },
    ) {
        AnimalList(
            animals = animalRepository.all(),
            navController = navController
        )
    }
}

@Composable
fun AnimalList(animals: List<Animal>, navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = animals) { animal ->
            ListItem(animal = animal) { id ->
                navController.navigate("detail/${id.value}")
            }
            Divider(color = colorResource(id = R.color.black))
        }
    }
}

@Composable
fun ListItem(animal: Animal, onClick: (Id) -> Unit) {
    Row(
        modifier = Modifier
            .clickable {
                onClick(animal.id)
            }
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        CoilImage(
            data = animal.image,
            contentDescription = "photo of ${animal.name}."
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = animal.name,
                style = typography.h5
            )
            Text(
                text = "Age:${animal.age}",
                style = typography.caption
            )
            Text(
                text = "Description:${animal.description}",
                style = typography.caption,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }
    }
}
