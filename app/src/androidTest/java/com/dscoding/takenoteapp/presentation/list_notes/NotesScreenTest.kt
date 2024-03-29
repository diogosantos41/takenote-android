@file:OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
package com.dscoding.takenoteapp.presentation.list_notes

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.common.TestTags
import com.dscoding.takenoteapp.di.DataModule
import com.dscoding.takenoteapp.di.UseCaseModule
import com.dscoding.takenoteapp.presentation.MainActivity
import com.dscoding.takenoteapp.presentation.util.Screen
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class, UseCaseModule::class)
class NotesScreenTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalFoundationApi::class)
    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            TakeNoteAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(route = Screen.NotesScreen.route) {
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Suppress
    @Test
    fun openOrderSection_isOrderSectionVisible() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION)
            .assertDoesNotExist()
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_sort))
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION)
            .assertIsDisplayed()
    }

    @Suppress
    @Test
    fun selectOrderSectionRadioButtonThenCloseOrderSection_isOrderSectionGone() {
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION)
            .assertDoesNotExist()
        composeRule.onNodeWithContentDescription(
            context.getString(R.string.notes_content_description_sort)
        )
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_TITLE_RADIO_BUTTON)
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_ASCENDING_RADIO_BUTTON)
            .performClick()
        composeRule.onNodeWithContentDescription(context.getString(R.string.notes_content_description_sort))
            .performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION)
            .assertDoesNotExist()
    }
}