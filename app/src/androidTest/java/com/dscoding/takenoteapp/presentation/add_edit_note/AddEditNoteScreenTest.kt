@file:OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
package com.dscoding.takenoteapp.presentation.add_edit_note

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
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
import com.dscoding.takenoteapp.ui.theme.LightPink
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class, UseCaseModule::class)
class AddEditNoteScreenTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            TakeNoteAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.AddEditNoteScreen.route
                ) {
                    composable(route = Screen.AddEditNoteScreen.route) {
                        AddEditNoteScreen(
                            navController = navController,
                            noteColor = LightPink.toArgb()
                        )
                    }
                }
            }
        }
    }

    @Test
    fun enteredNoteTitle_hasTitleTextFieldCorrectText() {
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .performTextInput("note-test-title")
        composeRule.onNodeWithTag(TestTags.TITLE_TEXT_FIELD)
            .assertTextEquals("note-test-title")
    }

    @Test
    fun enteredNoteContent_hasContentTextFieldCorrectText() {
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("note-test-content")
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .assertTextEquals("note-test-content")
    }

    @Test
    fun clickAddNoteWithoutTitle_isSnackbarDisplayingWithCorrectText() {
        composeRule.onNodeWithTag(TestTags.CONTENT_TEXT_FIELD)
            .performTextInput("note-test-content")
        composeRule.onNodeWithContentDescription(context.getString(R.string.add_edit_note_content_description_save))
            .performClick()
        composeRule.onNodeWithText(context.getString(R.string.error_add_note_empty_title))
            .assertIsDisplayed()
    }
}