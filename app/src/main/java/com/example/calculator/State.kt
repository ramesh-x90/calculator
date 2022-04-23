package com.example.calculator

import com.example.calculator.utility.Operators
import com.example.calculator.utility.PostFixExpression
import java.lang.Exception

object State {
    private var infixExpression = ""
    private var res = ""

    fun updateInfixExpression(str : String)
    {
        if(str.contains( Operators.pattern))
        {

            if(infixExpression.contains( Operators.patternFormEnd)  )
            {
                infixExpression = infixExpression.split( Operators.patternFormEnd)[0]
                updateInfixExpression( str )
                return
            }

            if( infixExpression.endsWith(".") )
            {
                infixExpression = infixExpression.split( Regex("""\.$""") )[0]
                updateInfixExpression( str )
                return
            }



        }

        if(str == ".")
        {
            if(infixExpression.contains( Regex("""(\.[\d]+|\.)$""") ))
                return
        }



        infixExpression += str


    }

    private fun setRes(num : Double)
    {
        res = num.toString()
    }

    fun clearState(){
        infixExpression = ""
        res = ""

    }

    fun removeLastChar()
    {
        try {
            infixExpression = infixExpression.substring(startIndex = 0 , endIndex = infixExpression.length - 1)

            if(infixExpression.isBlank())
            {
                clearState()
            }
        }catch (e : Exception){

        }

    }

    fun calculate()
    {
        try {
            setRes(     PostFixExpression(inFixExpression = infixExpression).calculate()    )
        }catch (e : Exception){}
    }

    fun getInfixExpression() : String = infixExpression

    fun getResult() : String = res

}