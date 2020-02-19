package com.riyad.p5.controller

import org.threeten.bp.LocalDate


class SearchManager {

    enum class UserInputState {

        VALID,
        NO_USER_INPUT,
        NO_SECTION_SELECTED,
        INCOHERENT_DATES

    }

    fun checkUserInput(
        searchInput: String,
        sections: ArrayList<String>,
        beginDate: LocalDate?,
        endDate: LocalDate?
    ): UserInputState {

        if (searchInput.isBlank()) {

            return UserInputState.NO_USER_INPUT

        }

        if (sections.isEmpty()) {

            return UserInputState.NO_SECTION_SELECTED

        }
        if (beginDate != null && endDate?.isBefore(beginDate) == true) {

            return UserInputState.INCOHERENT_DATES
        }




        return UserInputState.VALID
    }


}