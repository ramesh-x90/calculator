package com.example.calculator

import com.example.calculator.utility.PostFixExpression
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(117, PostFixExpression("12+10*11-5.").calculate().toInt())
    }
}