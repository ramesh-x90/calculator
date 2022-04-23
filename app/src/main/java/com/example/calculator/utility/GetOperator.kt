package com.example.calculator.utility

object GetOperator {
    operator fun invoke(symbol : String) : Operators
    {
        return when(symbol)
        {
            Operators.Sum.symbol -> Operators.Sum
            Operators.Minus.symbol -> Operators.Minus
            Operators.Divide.symbol -> Operators.Divide
            Operators.Multiply.symbol -> Operators.Multiply
            else -> throw Exception("error")
        }
    }
}