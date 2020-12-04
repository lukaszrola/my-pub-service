package com.purecode.user.entity

data class EmailAddress (val value: String) {
    override fun toString(): String = value
}