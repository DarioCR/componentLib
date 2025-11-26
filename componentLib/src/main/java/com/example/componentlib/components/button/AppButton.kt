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

// Describe los estilos visuales de botón soportados para que los tokens puedan resolver padding, forma,
// tipografía y colores de forma consistente en toda la librería.
sealed class AppButtonVariant {
    object Primary : AppButtonVariant()
    object Secondary : AppButtonVariant()
    object Tonal : AppButtonVariant()
    object Outlined : AppButtonVariant()
    object Text : AppButtonVariant()
    object Ghost : AppButtonVariant()
    object Extended : AppButtonVariant()
}

// Botón de alto nivel que alinea tokens, semántica de accesibilidad y manejo de clics con throttling.
// text: Etiqueta visible del botón.
// onClick: Lambda invocada cuando se presiona el botón (con throttling para evitar dobles taps).
// modifier: Modifier opcional para decorar el contenedor del botón.
// enabled: Cuando es false muestra colores deshabilitados e ignora clics.
// loading: Cuando es true reemplaza el contenido por un indicador de progreso y deshabilita clics.
// leadingIcon: Ícono opcional que se muestra antes del texto cuando no está cargando.
// trailingIcon: Ícono opcional que se muestra después del texto cuando no está cargando.
// variant: Elige la familia de tokens que define colores/tipografía/padding/forma.
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
    val clickableEnabled = enabled && !loading
    val throttledOnClick = rememberThrottledClick(enabled = clickableEnabled, onClick = onClick)

    Surface(
        modifier = modifier
            .buttonSemantics()
            .defaultMinSize(minHeight = minHeight, minWidth = ButtonTokens.minWidth),
        onClick = throttledOnClick,
        enabled = clickableEnabled,
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

// Construye el contenido de AppButton componiendo indicador de carga, texto animado e íconos
// en el orden prescrito por los tokens de diseño.
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

// Wrapper ligero usado para íconos leading y trailing para mantener la alineación consistente.
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
