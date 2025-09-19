package com.dscoding.takenoteapp.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dscoding.takenoteapp.R
import com.dscoding.takenoteapp.domain.data_store.SettingsDataStore
import com.dscoding.takenoteapp.domain.model.PreferencesDto
import com.dscoding.takenoteapp.ui.theme.TakeNoteAppTheme
import com.dscoding.takenoteapp.utils.Font
import com.dscoding.takenoteapp.utils.Theme
import com.dscoding.takenoteapp.utils.getAppFont
import com.dscoding.takenoteapp.utils.getAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiThemeState: UiThemeState by mutableStateOf(UiThemeState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                getUiThemeState()
                    .onEach {
                        uiThemeState = it
                    }
                    .collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiThemeState) {
                UiThemeState.Loading -> true
                is UiThemeState.Success -> false
            }
        }

        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        enableEdgeToEdge()
        setContent {
            TakeNoteAppTheme(
                theme = getAppTheme(getThemeId(uiThemeState)),
                font = getAppFont(getFontId(uiThemeState))
            ) {
                Surface {
                    // On some devices (ex: Poko X3) the screen became all white after system theme change.
                    // This code seems to solve the problem
                    lifecycleScope.launch {
                        delay(25)
                        window.setBackgroundDrawableResource(android.R.color.transparent)
                    }
                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun getThemeId(
        uiState: UiThemeState,
    ): Int = when (uiState) {
        is UiThemeState.Loading -> Theme.SystemDefault.id
        is UiThemeState.Success -> uiState.preferencesDto.theme
    }

    @Composable
    private fun getFontId(
        uiState: UiThemeState,
    ): Int = when (uiState) {
        is UiThemeState.Loading -> Font.Montserrat.id
        is UiThemeState.Success -> uiState.preferencesDto.font
    }

    private fun getUiThemeState(): StateFlow<UiThemeState> {
        return settingsDataStore.getPreferences().map {
            UiThemeState.Success(it)
        }.stateIn(
            scope = lifecycleScope,
            initialValue = UiThemeState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )
    }

    sealed interface UiThemeState {
        object Loading : UiThemeState
        data class Success(val preferencesDto: PreferencesDto) : UiThemeState
    }
}
