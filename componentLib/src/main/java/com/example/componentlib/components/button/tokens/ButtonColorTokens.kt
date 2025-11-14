package com.example.componentlib.components.button.tokens

import androidx.compose.ui.graphics.Color
import com.example.componentlib.components.button.ButtonColorScheme
import com.example.componentlib.components.button.FabColorScheme
import com.example.componentlib.components.button.IconButtonColorScheme

internal object ButtonColorTokens {
    private val PrimaryContainer = Color(0xFF1A4FE3)
    private val PrimaryContainerDisabled = Color(0xFF9CB7FF)
    private val SecondaryContainer = Color(0xFF1F2933)
    private val TonalContainer = Color(0xFFDDE7FF)
    private val TonalContent = Color(0xFF1E3E8E)
    private val OutlineContent = Color(0xFF1A4FE3)
    private val OutlineStroke = Color(0xFF4C6EF5)
    private val OutlineStrokeDisabled = Color(0xFF9AA5B1)
    private val TextContent = Color(0xFF1F5EFF)
    private val TextContentDisabled = Color(0xFFA0AEC0)
    private val GhostContent = Color(0xFF101828)
    private val ExtendedContainer = Color(0xFF2F9D8A)
    private val ExtendedContainerDisabled = Color(0xFFA5D9CF)
    private val IconContainer = Color(0xFFE7EDFF)
    private val IconContent = Color(0xFF1C3FAA)
    private val FabContainer = Color(0xFFFF6B4C)
    private val FabContainerDisabled = Color(0xFFFFB39F)
    private val ExtendedFabContainer = Color(0xFF3E63F4)
    private val ExtendedFabContainerDisabled = Color(0xFFA7B8FF)
    private val NeutralWhite = Color(0xFFFFFFFF)
    private val NeutralWhiteMuted = Color(0xB3FFFFFF)
    private val NeutralMuted = Color(0xFF8A94A7)
    private val NeutralSurface = Color(0xFFE4E7EC)
    private val LoadingTrackLightOnDark = Color(0x66FFFFFF)
    private val LoadingTrackOnLight = Color(0x331E3E8E)
    private val LoadingTrackOnOutline = Color(0x331A4FE3)
    private val LoadingTrackOnGhost = Color(0x33101828)
    private val Transparent = Color(0x00000000)

    val Primary = ButtonColorScheme(
        containerColor = PrimaryContainer,
        contentColor = NeutralWhite,
        borderColor = Transparent,
        disabledContainerColor = PrimaryContainerDisabled,
        disabledContentColor = NeutralWhiteMuted,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = NeutralWhite,
        loadingTrackColor = LoadingTrackLightOnDark
    )

    val Secondary = ButtonColorScheme(
        containerColor = SecondaryContainer,
        contentColor = NeutralWhite,
        borderColor = Transparent,
        disabledContainerColor = NeutralSurface,
        disabledContentColor = NeutralMuted,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = NeutralWhite,
        loadingTrackColor = LoadingTrackLightOnDark
    )

    val Tonal = ButtonColorScheme(
        containerColor = TonalContainer,
        contentColor = TonalContent,
        borderColor = Transparent,
        disabledContainerColor = NeutralSurface,
        disabledContentColor = NeutralMuted,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = TonalContent,
        loadingTrackColor = LoadingTrackOnLight
    )

    val Outlined = ButtonColorScheme(
        containerColor = Transparent,
        contentColor = OutlineContent,
        borderColor = OutlineStroke,
        disabledContainerColor = Transparent,
        disabledContentColor = NeutralMuted,
        disabledBorderColor = OutlineStrokeDisabled,
        loadingIndicatorColor = OutlineContent,
        loadingTrackColor = LoadingTrackOnOutline
    )

    val Text = ButtonColorScheme(
        containerColor = Transparent,
        contentColor = TextContent,
        borderColor = Transparent,
        disabledContainerColor = Transparent,
        disabledContentColor = TextContentDisabled,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = TextContent,
        loadingTrackColor = LoadingTrackOnOutline
    )

    val Ghost = ButtonColorScheme(
        containerColor = Transparent,
        contentColor = GhostContent,
        borderColor = Transparent,
        disabledContainerColor = Transparent,
        disabledContentColor = NeutralMuted,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = GhostContent,
        loadingTrackColor = LoadingTrackOnGhost
    )

    val Extended = ButtonColorScheme(
        containerColor = ExtendedContainer,
        contentColor = NeutralWhite,
        borderColor = Transparent,
        disabledContainerColor = ExtendedContainerDisabled,
        disabledContentColor = NeutralWhiteMuted,
        disabledBorderColor = Transparent,
        loadingIndicatorColor = NeutralWhite,
        loadingTrackColor = LoadingTrackLightOnDark
    )

    val IconButton = IconButtonColorScheme(
        containerColor = IconContainer,
        contentColor = IconContent,
        disabledContainerColor = NeutralSurface,
        disabledContentColor = NeutralMuted
    )

    val ToggleIconButtonOn = IconButtonColorScheme(
        containerColor = PrimaryContainer,
        contentColor = NeutralWhite,
        disabledContainerColor = PrimaryContainerDisabled,
        disabledContentColor = NeutralWhiteMuted
    )

    val ToggleIconButtonOff = IconButtonColorScheme(
        containerColor = IconContainer,
        contentColor = IconContent,
        disabledContainerColor = NeutralSurface,
        disabledContentColor = NeutralMuted
    )

    val Fab = FabColorScheme(
        containerColor = FabContainer,
        contentColor = NeutralWhite,
        disabledContainerColor = FabContainerDisabled,
        disabledContentColor = NeutralWhiteMuted
    )

    val ExtendedFab = FabColorScheme(
        containerColor = ExtendedFabContainer,
        contentColor = NeutralWhite,
        disabledContainerColor = ExtendedFabContainerDisabled,
        disabledContentColor = NeutralWhiteMuted
    )
}
