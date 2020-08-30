package com.example.musicdictionaryandroid.model.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Artist : RealmObject() {
    @PrimaryKey open var id: Int? = 0
    @Required open var name: String? = "アーティスト名"
    @Required open var gender: Int? = 0
    @Required open var voice: Int? = 0
    @Required open var length: Int? = 0
    @Required open var lyrics: Int? = 0
    open var genre1: String = "ジャンル１"
    open var genre2: String = "ジャンル２"
}
