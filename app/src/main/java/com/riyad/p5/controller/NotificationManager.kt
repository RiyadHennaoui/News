package com.riyad.p5.controller


class NotificationManager {

    enum class UserInputState {
        VALID,
        NO_USER_INPUT,
        NO_SECTION_SELECTED,
    }

    fun checkUserInput(
        searchInput: String,
        sections: ArrayList<String>
    ): UserInputState {

        if (searchInput.isBlank()) {
            return UserInputState.NO_USER_INPUT
        }
        if (sections.isEmpty()) {
            return UserInputState.NO_SECTION_SELECTED
        }
        return UserInputState.VALID
    }


}