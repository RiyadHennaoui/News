package com.riyad.p5

import com.riyad.p5.controller.NotificationManager
import com.riyad.p5.controller.NotificationManager.UserInputState.*
import org.junit.Assert
import org.junit.Test

class NotificationManagerTest {

    @Test
    fun `should return NO_USER_INPUT when user input is empty and one checkbox selected`() {
        //given

        val userInput = ""
        val checkBox = ArrayList(listOf("Business"))
        //when

        val result = NotificationManager()
            .checkUserInput(userInput, checkBox)
        //then

        Assert.assertEquals(NO_USER_INPUT, result)
    }
    @Test
    fun `should return NO_USER_INPUT when user input is empty and no checkbox selected`() {
        //given

        val userInput = ""
        val checkBox = ArrayList<String>()
        //when

        val result = NotificationManager()
            .checkUserInput(userInput, checkBox)
        //then

        Assert.assertEquals(NO_USER_INPUT, result)
    }

    @Test
    fun `should return NO_SECTION_SELECTED when user input is Trump but no checkbox selected`() {
        //given

        val userInput = "Trump"
        val checkBox = ArrayList<String>()
        //when

        val result = NotificationManager()
            .checkUserInput(userInput, checkBox)
        //then

        Assert.assertEquals(NO_SECTION_SELECTED, result)
    }

    @Test
    fun `should return VALID when user input is Trump and one checkbox selected`() {
        //given

        val userInput = "Trump"
        val checkBox = ArrayList(listOf("Business"))
        //when

        val result = NotificationManager()
            .checkUserInput(userInput, checkBox)
        //then

        Assert.assertEquals(VALID, result)
    }
}