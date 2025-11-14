package com.example.componentlib.components.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp

sealed class AppButtonVariant {
    object Primary : AppButtonVariant()
    object Secondary : AppButtonVariant()
    object Tonal : AppButtonVariant()
    object Outlined : AppButtonVariant()
    object Text : AppButtonVariant()
    object Ghost : AppButtonVariant()
    object Extended : AppButtonVariant()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    variant: AppButtonVariant = AppButtonVariant.Primary
) {
    val colorScheme = ButtonTokens.colors(variant)
    val contentPadding = ButtonTokens.padding(variant)
    val typography = ButtonTokens.typography(variant)
    val shape = ButtonTokens.shape(variant)
    val minHeight = ButtonTokens.minHeight(variant)
    val borderStroke = ButtonTokens.borderStroke(variant, enabled)

    val containerColor = if (enabled) colorScheme.containerColor else colorScheme.disabledContainerColor
    val contentColor = if (enabled) colorScheme.contentColor else colorScheme.disabledContentColor

    Surface(
        modifier = modifier
            .defaultMinSize(minHeight = minHeight, minWidth = ButtonTokens.minWidth),
        onClick = onClick,
        enabled = enabled && !loading,
        shape = shape,
        color = containerColor,
        contentColor = contentColor,
        border = borderStroke
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            ProvideTextStyle(value = typography) {
                ButtonContent(
                    modifier = Modifier
                        .defaultMinSize(minHeight = minHeight)
                        .padding(horizontal = contentPadding.horizontal, vertical = contentPadding.vertical),
                    text = text,
                    loading = loading,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon,
                    indicatorColor = colorScheme.loadingIndicatorColor,
                    indicatorTrackColor = colorScheme.loadingTrackColor
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ButtonContent(
    modifier: Modifier,
    text: String,
    loading: Boolean,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?,
    indicatorColor: Color,
    indicatorTrackColor: Color
) {
    val elements = mutableListOf<@Composable () -> Unit>()

    if (loading) {
        elements += {
            CircularProgressIndicator(
                modifier = Modifier.size(ButtonTokens.loadingIndicatorSize),
                color = indicatorColor,
                trackColor = indicatorTrackColor,
                strokeWidth = ButtonTokens.loadingIndicatorSize / ButtonTokens.loadingStrokeDivisor
            )
        }
    } else if (leadingIcon != null) {
        elements += {
            ButtonIcon(imageVector = leadingIcon, size = ButtonTokens.leadingIconSize)
        }
    }

    elements += {
        AnimatedContent(
            targetState = text,
            transitionSpec = {
                fadeIn(animationSpec = tween(durationMillis = ButtonTokens.stateTransitionMillis)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = ButtonTokens.stateTransitionMillis))
            },
            label = "app_button_text"
        ) { targetText ->
            Text(
                text = targetText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    if (trailingIcon != null && !loading) {
        elements += {
            ButtonIcon(imageVector = trailingIcon, size = ButtonTokens.trailingIconSize)
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(ButtonTokens.iconSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        elements.forEach { it() }
    }
}

@Composable
private fun ButtonIcon(imageVector: ImageVector, size: Dp) {
    Box(contentAlignment = Alignment.Center) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
}
