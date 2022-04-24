package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var inFixText : TextView
    private lateinit var resText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()

    }



    private fun initialize()
    {
        inFixText = findViewById<TextView>(R.id.infixExpression)
        resText = findViewById<TextView>(R.id.res)

        updateScreen()
    }




    private fun updateScreen()
    {
        inFixText.text = State.getInfixExpression()
        resText.text = State.getResult()

    }



    private fun clearScreen()
    {
        State.clearState()
        updateScreen()
    }




    private fun calculate()
    {
        State.calculate()
        updateScreen()
    }


    private fun backSpace() {
        State.removeLastChar()
        State.calculate()
        updateScreen()

    }




    fun onEvent(view: View)
    {
        when(view)
        {
            is Button -> {
                when(val text = view.text as String) {

                    "CE" -> clearScreen()

                    "=" -> calculate()

                    else -> {
                        State.updateInfixExpression(text)
                        State.calculate()
                        updateScreen()
                    }


                }

                if(view.id == R.id.button || view.id == R.id.button19){
                    backSpace()

                }
            }
            else -> {}
        }
    }


}