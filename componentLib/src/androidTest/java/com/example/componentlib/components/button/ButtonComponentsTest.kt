package com.example.componentlib.components.button

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.ui.Modifier
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
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonComponentsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun appButton_exposesRoleAndRespondsToClicks() {
        var clicks = 0

        composeTestRule.setContent {
            AppButton(
                text = "Enviar",
                onClick = { clicks++ },
                modifier = Modifier.testTag("primary_button")
            )
        }

        val node = composeTestRule.onNodeWithTag("primary_button")
        node.assertHasClickAction()
        node.assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))

        node.performClick()

        composeTestRule.runOnIdle {
            assertEquals(1, clicks)
        }
    }

    @Test
    fun appButton_loadingStateIsNotEnabled() {
        composeTestRule.setContent {
            AppButton(
                text = "Cargando",
                onClick = {},
                loading = true,
                modifier = Modifier.testTag("loading_button")
            )
        }

        composeTestRule.onNodeWithTag("loading_button").assertIsNotEnabled()
    }

    @Test
    fun appIconButton_setsContentDescription() {
        composeTestRule.setContent {
            AppIconButton(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add item",
                onClick = {},
                modifier = Modifier.testTag("icon_button")
            )
        }

        composeTestRule.onNodeWithTag("icon_button")
            .assertHasClickAction()
            .assertContentDescriptionEquals("Add item")
    }

    @Test
    fun appToggleIconButton_reportsToggleStateAndFiresCallback() {
        var latestCheckedState = false

        composeTestRule.setContent {
            AppToggleIconButton(
                checked = true,
                onCheckedChange = { latestCheckedState = it },
                checkedIcon = Icons.Rounded.PlayArrow,
                contentDescription = "Toggle",
                modifier = Modifier.testTag("toggle_icon")
            )
        }

        val toggleNode = composeTestRule.onNodeWithTag("toggle_icon")
        toggleNode.assertIsToggleable()
        toggleNode.assert(
            SemanticsMatcher.expectValue(SemanticsProperties.ToggleableState, ToggleableState.On)
        )

        toggleNode.performClick()

        composeTestRule.runOnIdle {
            assertTrue(latestCheckedState)
        }
    }
}
