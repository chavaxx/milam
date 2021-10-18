package com.example.findnumber

data class LetterButon(
    val letter : Char,  //it determines which letter is going to render
    var isSelected: Boolean = false //it determines if letter has been selected
)