package com.example.componentlib.components.button

// Pruebas de UI y accesibilidad para AppIconButton y AppToggleIconButton.
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsToggleable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppIconButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    // Verifica que AppIconButton exponga rol de botón, descripción y maneje clics.
    @Test
    fun appIconButton_exposesRoleAndRespondsToClicks() {
        var clicks = 0

        composeTestRule.setContent {
            AppIconButton(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Añadir elemento",
                onClick = { clicks++ },
                modifier = Modifier.testTag("icon_button_accessibility")
            )
        }

        val node = composeTestRule.onNodeWithTag("icon_button_accessibility")
        node.assertHasClickAction()
        node.assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
        node.assertContentDescriptionEquals("Añadir elemento")

        node.performClick()

        composeTestRule.runOnIdle {
            assertEquals(1, clicks)
        }
    }

    // Verifica que el estado deshabilitado se refleje en la semántica de accesibilidad.
    @Test
    fun appIconButton_disabledStateIsSurfaceLevelA11ySignal() {
        composeTestRule.setContent {
            AppIconButton(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Acción deshabilitada",
                onClick = {},
                enabled = false,
                modifier = Modifier.testTag("icon_button_disabled")
            )
        }

        composeTestRule.onNodeWithTag("icon_button_disabled")
            .assertIsNotEnabled()
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
    }

    // Verifica que rememberThrottledClick bloquee taps rápidos hasta que pase el intervalo.
    @Test
    fun rememberThrottledClick_blocksRapidReTapsUntilIntervalPasses() {
        val fakeTimeSource = FakeTimeSource()
        var clicks = 0

        composeTestRule.setContent {
            TestThrottledSurface(
                enabled = true,
                throttleIntervalMillis = 500L,
                timeSource = fakeTimeSource::now,
                onClick = { clicks++ }
            )
        }

        val node = composeTestRule.onNodeWithTag("throttled_surface")

        node.performClick()
        composeTestRule.runOnIdle { assertEquals(1, clicks) }

        composeTestRule.runOnIdle { fakeTimeSource.advanceBy(100L) }
        node.performClick()
        composeTestRule.runOnIdle { assertEquals(1, clicks) }

        composeTestRule.runOnIdle { fakeTimeSource.advanceBy(500L) }
        node.performClick()
        composeTestRule.runOnIdle { assertEquals(2, clicks) }
    }

    // Verifica que AppToggleIconButton anuncie correctamente su estado a TalkBack.
    @Test
    fun appToggleIconButton_announcesTalkBackState() {
        var callbackInvoked = false

        composeTestRule.setContent {
            AppToggleIconButton(
                checked = true,
                onCheckedChange = { callbackInvoked = true },
                checkedIcon = Icons.Rounded.PlayArrow,
                contentDescription = "Reproducir",
                modifier = Modifier.testTag("toggle_icon_button")
            )
        }

        val node = composeTestRule.onNodeWithTag("toggle_icon_button")
        node.assertIsToggleable()
        node.assert(
            SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch)
        )
        node.assert(
            SemanticsMatcher.expectValue(SemanticsProperties.ToggleableState, ToggleableState.On)
        )
        node.assertContentDescriptionEquals("Reproducir")

        node.performClick()
        composeTestRule.runOnIdle { assertTrue(callbackInvoked) }
    }

    // Superficie de prueba que usa rememberThrottledClick para validar el throttling.
    @Composable
    private fun TestThrottledSurface(
        enabled: Boolean,
        throttleIntervalMillis: Long,
        timeSource: () -> Long,
        onClick: () -> Unit
    ) {
        val throttledOnClick = rememberThrottledClick(
            enabled = enabled,
            throttleIntervalMillis = throttleIntervalMillis,
            onClick = onClick,
            timeSource = timeSource
        )

        Box(
            modifier = Modifier
                .testTag("throttled_surface")
                .size(ButtonTokens.iconButtonSize)
                .buttonSemantics(contentDescription = "Botón con throttling")
                .background(Color.Transparent)
                .clickable(enabled = enabled, onClick = throttledOnClick)
        )
    }

    // Fuente de tiempo fake para controlar el avance en las pruebas de throttling.
    // El valor inicial distinto de cero garantiza que el primer tap sea aceptado.
    private class FakeTimeSource(initial: Long = 1_000L) {
        private var time = initial

        fun now(): Long = time

        fun advanceBy(intervalMillis: Long) {
            time += intervalMillis
        }
    }
}
