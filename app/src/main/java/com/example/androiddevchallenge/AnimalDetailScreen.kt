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

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.AnimalRepository
import com.example.androiddevchallenge.model.Id
import com.example.androiddevchallenge.ui.theme.typography
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun AnimalDetailScreen(id: Id, animalRepository: AnimalRepository, navController: NavController) {
    val animal = animalRepository.findById(id)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(animal?.name ?: stringResource(id = R.string.not_found))
                }
            )
        }
    ) {
        animal?.let {
            AnimalDetail(animal = animal, animalRepository = animalRepository, navController = navController)
        } ?: NotFound()
    }
}

@Composable
fun NotFound() {
    Text(stringResource(id = R.string.not_found))
}

@Composable
fun AnimalDetail(animal: Animal, animalRepository: AnimalRepository, navController: NavController) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CoilImage(
            data = animal.image,
            contentDescription = "${animal.name}'s photo.",
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        )
        Text(
            text = animal.name,
            style = typography.h3,
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = "Sex: ${animal.sex}",
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = animal.description,
            modifier = Modifier.padding(top = 8.dp),
        )

        PedigreeTable(
            animal = animal,
            animalRepository = animalRepository,
            modifier = Modifier.padding(top = 8.dp),
        ) { id ->
            navController.navigate("detail/${id.value}")
        }
    }
}

@Composable
fun UnknownText() {
    Text(
        text = stringResource(id = R.string.unknown),
        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
    )
}

// FIXME Layout
@Composable
fun Parent(animal: Animal, animalRepository: AnimalRepository, onClick: (Id) -> Unit, generation: Int = 0) {
    if (generation >= 3) return
    val father = animal.father?.let { animalRepository.findById(it) }
    val mother = animal.mother?.let { animalRepository.findById(it) }

    Column(
        Modifier
            .border(
                width = 1.dp,
                color = Color.Black
            )
            .fillMaxWidth()
    ) {
        father?.let {
            Row(
                Modifier.fillMaxWidth()
            ) {
                Column(Modifier.clickable { onClick(it.id) }) {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Blue,
                    )
                }
                Parent(
                    animal = it,
                    animalRepository = animalRepository,
                    onClick = onClick,
                    generation = generation + 1
                )
            }
        } ?: UnknownText()

        mother?.let {
            Row(
                Modifier.fillMaxWidth()
            ) {
                Column(Modifier.clickable { onClick(it.id) }) {
                    Text(
                        text = it.name,
                        modifier = Modifier.padding(start = 8.dp),
                        color = Color.Magenta,
                    )
                }
                Parent(
                    animal = it,
                    animalRepository = animalRepository,
                    onClick = onClick,
                    generation = generation + 1
                )
            }
        } ?: UnknownText()
    }
}

@Composable
fun PedigreeTable(
    animal: Animal,
    animalRepository: AnimalRepository,
    modifier: Modifier = Modifier,
    onClick: (Id) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Parent(animal = animal, animalRepository = animalRepository, onClick = onClick)
    }
}
