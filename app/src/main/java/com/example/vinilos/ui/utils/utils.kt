package com.example.vinilos.ui.utils

val lengthMaxIsValid: (String, Int) -> Boolean = {i: String, l: Int -> i.length <= l}
val lengthMinIsValid: (String, Int) -> Boolean = {i: String, l: Int -> i.length >= l}


fun validateInput(maxLength: Int, minLength: Int, input: String): Boolean {
    return lengthMaxIsValid(input, maxLength) && lengthMinIsValid(input, minLength)
}

