package com.example.blimblam.model

import java.io.Serializable

data class Origin( val name: String, val url: String) : Serializable{
    override fun toString(): String {
        return "Name: $name\nURL: $url"
    }
}
