package com.example.calculator.utility

sealed class Operators(
    val symbol : String,
    val presidency : Int,
    val associativity : Associativity,
    val operation : (a : Double , b : Double) -> Double
) {
    object Sum  : Operators("+" , 0 , Associativity.LEFT_TO_RIGHT, operation = { a, b -> a + b})
    object Minus : Operators("-" , 0 , Associativity.LEFT_TO_RIGHT, operation = { a, b -> a - b })
    object Divide : Operators("/" , 1 , Associativity.LEFT_TO_RIGHT, operation = { a, b -> a / b })
    object Multiply : Operators("*" , 1 , Associativity.LEFT_TO_RIGHT, operation = { a, b -> a * b })
    object Euq : Operators("=" , -1 , Associativity.RIGHT_TO_LEFT, operation = { _, b -> b })

    companion object
    {
        const val symbols = """*+\-/"""

        val pattern = Regex("[$symbols]+")
        val patternFormEnd = Regex("[$symbols]+$")
        val patternFormStart = Regex("^[$symbols]+")
    }
}
