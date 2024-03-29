@file:OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
package com.dscoding.takenoteapp

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dscoding.takenoteapp.presentation.MainActivity
import java.util.Timer
import junit.framework.Assert
import kotlin.concurrent.schedule

fun assertNodeEqualsText(node: SemanticsNodeInteraction, text: String) {
    for ((key, value) in node.fetchSemanticsNode().config) {
        if (key.name == "Text") {
            Assert.assertEquals(
                text, value.toString().replace("[", "").replace("]", "").trim()
            )
        }
    }
}

object AsyncTimer {
    var expired = false
    fun start(delayTime: Long) {
        expired = false
        Timer().schedule(delayTime) {
            expired = true
        }
    }
}

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.delay(delayTime: Long) {
    AsyncTimer.start(delayTime)
    this.waitUntil(
        condition = { AsyncTimer.expired }, timeoutMillis = delayTime + 1000
    )
}
