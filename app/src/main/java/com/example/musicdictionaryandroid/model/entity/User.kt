package com.example.musicdictionaryandroid.model.entity

data class User (
    val email: String,
    val name: String,
    val gender: Int,
    val area: Int,
    val birthday: String,
    val artist_count: Int
) : java.io.Serializable