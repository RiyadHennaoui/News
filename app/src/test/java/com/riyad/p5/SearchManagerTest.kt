package com.riyad.p5;

import com.riyad.p5.controller.SearchManager
import com.riyad.p5.controller.SearchManager.UserInputState.*
import org.junit.Assert
import org.junit.Test;
import org.threeten.bp.LocalDate


class SearchManagerTest {

    @Test
    fun `should return NO_USER_INPUT when user input is empty and one checkbox selected`() {

        // given
        val userInput = ""
        val checkBoxes = ArrayList(listOf("Business"))
        val beginDate = null
        val endDate = null


        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkBoxes, beginDate, endDate)

        // then

        Assert.assertEquals(NO_USER_INPUT, result)
    }

    @Test
    fun `should return NO_USER_INPUT when user input is empty and no checkboxes selected`() {

        // given
        val userInput = ""
        val checkBoxes = ArrayList<String>()
        val beginDate = null
        val endDate = null

        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkBoxes, beginDate, endDate)

        // then

        Assert.assertEquals(NO_USER_INPUT, result)
    }

    @Test
    fun `should return NO_SECTION_SELECTED when user input is courgette but no checkbox selected`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList<String>()
        val beginDate = null
        val endDate = null
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes, beginDate, endDate)

        // then

        Assert.assertEquals(NO_SECTION_SELECTED, result)
    }

    @Test
    fun `should return VALID when user input is courgette and one checkbox selected`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = null
        val endDate = null
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }

    @Test
    fun `should return INCOHERANT_DATES when endDate is inferior to beginDate`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = LocalDate.of(2018, 8, 12)
        val endDate = LocalDate.of(2018, 7, 12)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(INCOHERENT_DATES, result)
    }


    @Test
    fun `should return INCOHERANT_DATES when endDate is inferior to beginDate bis`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = LocalDate.of(2018, 8, 12)
        val endDate = LocalDate.of(2018, 8, 11)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(INCOHERENT_DATES, result)
    }
    @Test
    fun `should return INCOHERANT_DATES when endDate is inferior to beginDate ter`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = LocalDate.of(2016, 3, 1)
        val endDate = LocalDate.of(2016, 2, 29)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(INCOHERENT_DATES, result)
    }

    @Test
    fun `should return VALID when user input is courgette and one checkbox selected and Dates are correct`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = LocalDate.of(2018, 8, 12)
        val endDate = LocalDate.of(2018, 8, 13)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }
    @Test
    fun `should return VALID when user input is courgette and one checkbox selected and beginDate is correct`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = LocalDate.of(2018, 8, 12)
        val endDate = null
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }

    @Test
    fun `should return VALID when user input is courgette and one checkbox selected and endDate is correct`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = null
        val endDate = LocalDate.of(2018, 8, 13)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes,beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }

    @Test
    fun `should return INCOHERENT_DATES when end date is before begin date`() {

        // given
        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val endDate = LocalDate.of(2019,8,12)
        val beginDate = LocalDate.of(2018,8, 12)

        // when

        val result = SearchManager()
            .checkUserInput(userInput,checkboxes,endDate,beginDate)

        // then

        Assert.assertEquals(INCOHERENT_DATES, result)
    }
    @Test
    fun `should return VALID when beguin date and end date are good `() {

        // given

        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val endDate = LocalDate.of(2019,8,12)
        val beginDate = LocalDate.of(2019,7, 12)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes, beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }

    @Test
    fun `should return VALID when end date is good `() {

        // given

        val userInput = "courgette"
        val checkboxes = ArrayList(listOf("Business"))
        val beginDate = null
        val endDate = LocalDate.of(2019,8,12)
        // when

        val result = SearchManager()
            .checkUserInput(userInput, checkboxes, beginDate, endDate)

        // then

        Assert.assertEquals(VALID, result)
    }



}
