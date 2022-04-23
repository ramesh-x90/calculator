package com.example.calculator.utility

class PostFixExpression (private val inFixExpression : String ) {

    private val postFixExpression :  MutableList<Any> = mutableListOf()

    private var numList: List<Double>
    private var operatorsList : List<Operators>

    init {
        validateInfix()

        numList = inFixExpression.split(Operators.pattern).filter{ it.isNotBlank() }.map{it.toDouble()}
        operatorsList =  inFixExpression.split( Regex("""[\d.]+""") ).filter{ it.isNotBlank() }.map{ GetOperator(it) }

        validatePostFix()

        convert()

    }

    private fun validateInfix()
    {
        if(
            inFixExpression.contains(Operators.patternFormStart) ||
            inFixExpression.contains(Operators.patternFormEnd)
        ) throw Exception("invalid infix expression")
    }


    private fun validatePostFix()
    {
        if(numList.size -1 != operatorsList.size) throw Exception("invalid infix expression")
    }

    private fun convert()
    {
        postFixExpression.clear()

        val stack = mutableListOf<Operators>()


        fun updateStack(_operator : Operators)
        {
            if(stack.isEmpty() || stack.last().presidency < _operator.presidency)
            {
                stack.add(_operator)
            }else if(stack.last().presidency > _operator.presidency){
                postFixExpression.add(stack.removeLast())
                updateStack( _operator)
            }else{
                when(stack.last().associativity)
                {
                    Associativity.LEFT_TO_RIGHT -> {
                        postFixExpression.add(stack.removeLast())
                        updateStack( _operator)
                    }

                    else -> {
                        stack.add(_operator)
                    }
                }
            }
        }


        var courser = 0

        numList.forEach{
            postFixExpression.add(it)

            if(courser < operatorsList.size)
            {
                val operator = operatorsList[courser]
                updateStack(operator)

            }

            courser ++

        }


        println(stack)

        if(stack.isNotEmpty())
        {
            stack.reversed().forEach{
                postFixExpression.add(it)
            }
        }

    }



    fun calculate() : Double
    {
        val stack = mutableListOf<Double>()

        postFixExpression.forEach{

            when(it)
            {
                is Operators -> {
                    if(stack.size > 1){
                        val lastNum = stack.removeLast()
                        val res = it.operation(stack.removeLast() , lastNum)
                        stack.add(res)
                    }

                }

                is Double -> {
                    stack.add(it)
                }
            }
        }

        if (stack.size == 1)
        {
            return stack[0]
        }

        throw Exception("error")



    }
}