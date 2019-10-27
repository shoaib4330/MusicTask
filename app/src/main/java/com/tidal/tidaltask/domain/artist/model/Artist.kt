package com.tidal.tidaltask.domain.artist.model

class Artist {
    val name: String
    val age: Int
    val thumbLink: String

    constructor(name: String, age: Int, thumbLink: String) {
        this.name = name
        this.age = age
        this.thumbLink = thumbLink
    }
}