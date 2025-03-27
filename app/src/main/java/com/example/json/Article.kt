package com.example.json

data class Article(
    val title: Title,
    val link: String,
    val date: String,
    val content: Content
)

data class Title(
    val rendered: String
)

data class Content(
    val rendered: String
)