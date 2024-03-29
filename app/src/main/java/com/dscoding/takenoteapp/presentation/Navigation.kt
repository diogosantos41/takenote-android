package com.dscoding.takenoteapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dscoding.takenoteapp.common.Constants.NAVIGATION_ANIMATION_DURATION
import com.dscoding.takenoteapp.common.Constants.NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET
import com.dscoding.takenoteapp.common.Constants.NOTE_COLOR_ARG
import com.dscoding.takenoteapp.common.Constants.NOTE_ID_ARG
import com.dscoding.takenoteapp.common.Constants.NOTE_INVALID_COLOR
import com.dscoding.takenoteapp.common.Constants.NOTE_INVALID_ID
import com.dscoding.takenoteapp.presentation.add_edit_note.AddEditNoteScreen
import com.dscoding.takenoteapp.presentation.list_notes.NotesScreen
import com.dscoding.takenoteapp.presentation.search_notes.SearchNotesScreen
import com.dscoding.takenoteapp.presentation.settings.SettingsScreen
import com.dscoding.takenoteapp.presentation.util.Screen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(
            route = Screen.NotesScreen.route,
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
        ) {
            NotesScreen(navController = navController)
        }
        composable(
            route = Screen.AddEditNoteScreen.withArgs(
                noteId = "{$NOTE_ID_ARG}",
                noteColor = "{$NOTE_COLOR_ARG}"
            ),
            arguments = listOf(
                navArgument(
                    name = NOTE_ID_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = NOTE_INVALID_ID
                },
                navArgument(
                    name = NOTE_COLOR_ARG
                ) {
                    type = NavType.IntType
                    defaultValue = NOTE_INVALID_COLOR
                },
            ),
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) }
        ) {
            val color = it.arguments?.getInt(NOTE_COLOR_ARG) ?: NOTE_INVALID_COLOR
            AddEditNoteScreen(
                navController = navController,
                noteColor = color
            )
        }
        composable(
            route = Screen.SearchNotesScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET },
                    animationSpec = tween(
                        durationMillis = NAVIGATION_ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { NAVIGATION_SLIDE_HORIZONTAL_ANIMATION_TARGET_OFFSET },
                    animationSpec = tween(
                        durationMillis = NAVIGATION_ANIMATION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) {
            SearchNotesScreen(navController = navController)
        }
        composable(
            route = Screen.SettingsScreen.route,
            exitTransition = { fadeOut(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) },
            enterTransition = { fadeIn(animationSpec = tween(durationMillis = NAVIGATION_ANIMATION_DURATION)) }
        ) {
            SettingsScreen(navController = navController)
        }
    }
}