package com.example.blimblam.model

data class Info( val count : Int, val pages : Int, val next : String, val prev : String) {

    override fun toString(): String {
        return "Count: $count\nPages: $pages\nNext: $next\nPrev: $prev"
    }
}
