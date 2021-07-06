package com.example.blimblam.model

data class Origin( val name: String, val url: String) {
    override fun toString(): String {
        return "Name: $name\nURL: $url"
    }
}
