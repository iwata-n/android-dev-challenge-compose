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
package com.example.androiddevchallenge.model

class AnimalRepository {

    // TODO DB
    private val list = listOf(
        Animal(
            id = Id("1"),
            name = "taro",
            sex = Sex.MALE,
            image = "https://picsum.photos/300/300",
            age = 2,
            description = "description"
        ),
        Animal(
            id = Id("2"),
            name = "hana",
            sex = Sex.FEMALE,
            image = "https://picsum.photos/300/300",
            age = 3,
            description = "description.\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        ),
        Animal(
            id = Id("3"),
            name = "jiro",
            sex = Sex.MALE,
            image = "https://picsum.photos/300/300",
            age = 3,
            father = Id("1"),
            description = "description.\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        ),
        Animal(
            id = Id("4"),
            name = "pochi",
            sex = Sex.MALE,
            image = "https://picsum.photos/300/300",
            age = 3,
            mother = Id("2"),
            father = Id("3"),
            description = "description.\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        ),
        Animal(
            id = Id("5"),
            name = "sakura",
            sex = Sex.FEMALE,
            image = "https://picsum.photos/300/300",
            age = 3,
            mother = Id("1"),
            father = Id("2"),
            description = "description.\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        ),
        Animal(
            id = Id("6"),
            name = "dai-goro",
            sex = Sex.MALE,
            image = "https://picsum.photos/300/300",
            age = 3,
            mother = Id("5"),
            father = Id("4"),
            description = "description.\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
        ),
    )

    fun findById(id: Id): Animal? = list.find { it.id == id }

    fun all(): List<Animal> = list
}
