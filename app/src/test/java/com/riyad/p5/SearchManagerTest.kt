package com.riyad.p5;

import org.junit.Assert
import org.junit.Test;

class SearchManagerTest {

    @Test
    fun `should return false when user input is empty`(){

        // given
        val userInput = ""

        // when

        val result = SearchManager().checkUserInput(userInput)

        // then

        Assert.assertFalse(result)
    }


}
