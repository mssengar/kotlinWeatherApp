package com.mss.weatherapp.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myUIComponentTest() {
        composeTestRule.setContent {
            MyUIComponent()
        }

        composeTestRule.onNodeWithText("Button").performClick()

        composeTestRule.onNodeWithText("Clicked!").assertIsDisplayed()
    }
}