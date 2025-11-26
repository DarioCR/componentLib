package com.example.componentlib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.componentlib.components.button.AppButton
import com.example.componentlib.components.button.AppButtonVariant
import com.example.componentlib.components.button.AppExtendedFab
import com.example.componentlib.components.button.AppFab
import com.example.componentlib.components.button.AppIconButton
import com.example.componentlib.components.button.AppToggleIconButton

// Pantallas disponibles dentro de la demo de botones.
private enum class DemoScreen {
    Home,
    AppButtons,
    IconButtons,
    FabButtons
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ButtonDemoHost()
                }
            }
        }
    }
}

// Punto de entrada de la jerarquía de navegación de la demo.
@Composable
private fun ButtonDemoHost() {
    val (screen, setScreen) = remember { mutableStateOf(DemoScreen.Home) }

    when (screen) {
        DemoScreen.Home -> HomeScreen(onNavigate = setScreen)
        DemoScreen.AppButtons -> ButtonsVariantsScreen(onBack = { setScreen(DemoScreen.Home) })
        DemoScreen.IconButtons -> IconButtonsScreen(onBack = { setScreen(DemoScreen.Home) })
        DemoScreen.FabButtons -> FabButtonsScreen(onBack = { setScreen(DemoScreen.Home) })
    }
}

// Pantalla inicial que permite navegar a los ejemplos de cada tipo de botón.
@Composable
private fun HomeScreen(onNavigate: (DemoScreen) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Demo de botones", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            text = "Ver AppButtons",
            onClick = { onNavigate(DemoScreen.AppButtons) },
            modifier = Modifier.fillMaxWidth()
        )
        AppButton(
            text = "Ver IconButtons",
            onClick = { onNavigate(DemoScreen.IconButtons) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.Add
        )
        AppButton(
            text = "Ver FABs",
            onClick = { onNavigate(DemoScreen.FabButtons) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.PlayArrow
        )
    }
}

// Pantalla que muestra las variantes de AppButton, registra acciones y simula un flujo de carga.
@Composable
private fun ButtonsVariantsScreen(onBack: () -> Unit) {
    val (lastAction, setLastAction) = remember { mutableStateOf("Ninguna acción aún") }
    val (isLoading, setIsLoading) = remember { mutableStateOf(false) }
    // Historial simple de las acciones realizadas en esta pantalla.
    val (acciones, setAcciones) = remember { mutableStateOf(listOf<String>()) }
    // Controla si el panel extendido está abierto o cerrado.
    val (mostrarPanelExtendido, setMostrarPanelExtendido) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton(
            text = "Volver",
            onClick = onBack,
            leadingIcon = Icons.Rounded.ArrowBack,
            variant = AppButtonVariant.Text,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Última acción: $lastAction",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        AppButton(
            text = "Primary",
            onClick = { setLastAction("Click en Primary") },
            variant = AppButtonVariant.Primary,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.Send
        )
        AppButton(
            text = "Secondary",
            onClick = {
                setLastAction("Click en Secondary")
                setAcciones(acciones + "Se guardó un borrador (Secondary)")
            },
            variant = AppButtonVariant.Secondary,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.Refresh
        )
        AppButton(
            text = "Tonal",
            onClick = {
                setLastAction("Click en Tonal")
                setAcciones(acciones + "Se actualizó la vista (Tonal)")
            },
            variant = AppButtonVariant.Tonal,
            modifier = Modifier.fillMaxWidth()
        )
        AppButton(
            text = "Outlined",
            onClick = {
                setLastAction("Click en Outlined")
                setAcciones(acciones + "Se canceló la acción (Outlined)")
            },
            variant = AppButtonVariant.Outlined,
            modifier = Modifier.fillMaxWidth()
        )
        AppButton(
            text = "Text",
            onClick = {
                setLastAction("Click en Text")
                setAcciones(acciones + "Se mostró ayuda (Text)")
            },
            variant = AppButtonVariant.Text,
            modifier = Modifier.fillMaxWidth()
        )
        AppButton(
            text = "Ghost",
            onClick = {
                setLastAction("Click en Ghost")
                setAcciones(acciones + "Se ejecutó una acción secundaria (Ghost)")
            },
            variant = AppButtonVariant.Ghost,
            modifier = Modifier.fillMaxWidth()
        )
        // Ejemplo de AppButton en estado deshabilitado.
        AppButton(
            text = "Primary deshabilitado",
            onClick = {},
            enabled = false,
            variant = AppButtonVariant.Primary,
            modifier = Modifier.fillMaxWidth()
        )
        AppButton(
            text = "Extended",
            onClick = {
                val siguiente = !mostrarPanelExtendido
                setMostrarPanelExtendido(siguiente)
                setLastAction(if (siguiente) "Panel extendido abierto" else "Panel extendido cerrado")
                setAcciones(
                    acciones + if (siguiente) "Se abrió el panel de acciones avanzadas" else "Se cerró el panel de acciones avanzadas"
                )
            },
            variant = AppButtonVariant.Extended,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = Icons.Rounded.PlayArrow,
            trailingIcon = Icons.Rounded.MoreVert
        )

        // Panel que representa contenido adicional controlado por el botón Extended.
        if (mostrarPanelExtendido) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Panel extendido: acciones rápidas",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButton(
                        text = "Acción 1",
                        onClick = {
                            setLastAction("Acción 1 desde panel extendido")
                            setAcciones(acciones + "Panel extendido: Acción 1 ejecutada")
                        },
                        variant = AppButtonVariant.Text
                    )
                    AppButton(
                        text = "Acción 2",
                        onClick = {
                            setLastAction("Acción 2 desde panel extendido")
                            setAcciones(acciones + "Panel extendido: Acción 2 ejecutada")
                        },
                        variant = AppButtonVariant.Text
                    )
                }
            }
        }

        AppButton(
            text = if (isLoading) "Cargando..." else "Simular carga",
            onClick = {
                val next = !isLoading
                setIsLoading(next)
                setLastAction(if (next) "Carga iniciada" else "Carga detenida")
                setAcciones(
                    acciones + if (next) "Se inició una operación de carga" else "Se canceló la operación de carga"
                )
            },
            loading = isLoading,
            variant = AppButtonVariant.Primary,
            modifier = Modifier.fillMaxWidth()
        )

        // Muestra las últimas acciones registradas para ver cómo responden los botones.
        if (acciones.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Historial de acciones:", style = MaterialTheme.typography.titleSmall)
            acciones.takeLast(5).forEach { entrada ->
                Text(text = "- $entrada", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

// Pantalla que muestra AppIconButton y AppToggleIconButton reaccionando a los clics.
@Composable
private fun IconButtonsScreen(onBack: () -> Unit) {
    val (addClicks, setAddClicks) = remember { mutableStateOf(0) }
    val toggleState = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton(
            text = "Volver",
            onClick = onBack,
            leadingIcon = Icons.Rounded.ArrowBack,
            variant = AppButtonVariant.Text,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppIconButton(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Añadir",
                onClick = { setAddClicks(addClicks + 1) }
            )
            AppIconButton(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Cerrar deshabilitado",
                onClick = {},
                enabled = false
            )
        }

        Text(text = "Clicks en Añadir: $addClicks")

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppToggleIconButton(
                checked = toggleState.value,
                onCheckedChange = { checked -> toggleState.value = checked },
                checkedIcon = Icons.Rounded.Pause,
                uncheckedIcon = Icons.Rounded.PlayArrow,
                contentDescription = "Reproducir / Pausar"
            )
            Text(text = if (toggleState.value) "Estado: ON" else "Estado: OFF")
        }

        // Ejemplo adicional de AppToggleIconButton deshabilitado.
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppToggleIconButton(
                checked = false,
                onCheckedChange = {},
                checkedIcon = Icons.Rounded.Pause,
                uncheckedIcon = Icons.Rounded.PlayArrow,
                contentDescription = "Toggle deshabilitado",
                enabled = false
            )
            Text(text = "Toggle deshabilitado")
        }
    }
}

// Pantalla que muestra AppFab y AppExtendedFab modificando contadores, estados y una lista de elementos.
@Composable
private fun FabButtonsScreen(onBack: () -> Unit) {
    val (fabClicks, setFabClicks) = remember { mutableStateOf(0) }
    val (extendedActive, setExtendedActive) = remember { mutableStateOf(false) }
    // Lista ficticia de elementos creada desde los FAB.
    val (items, setItems) = remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppButton(
            text = "Volver",
            onClick = onBack,
            leadingIcon = Icons.Rounded.ArrowBack,
            variant = AppButtonVariant.Text,
            modifier = Modifier.fillMaxWidth()
        )

        AppFab(
            icon = Icons.Rounded.Add,
            contentDescription = "Agregar",
            onClick = {
                val nuevoIndice = items.size + 1
                setFabClicks(fabClicks + 1)
                setItems(items + "Elemento $nuevoIndice")
            }
        )

        Text(text = "Clicks en FAB: $fabClicks")

        AppExtendedFab(
            text = if (extendedActive) "Limpiar lista" else "Crear elemento destacado",
            icon = Icons.Rounded.Add,
            onClick = {
                val siguiente = !extendedActive
                setExtendedActive(siguiente)
                if (!siguiente) {
                    // Cuando se desactiva, se limpia la lista para simular un reseteo de estado.
                    setItems(emptyList())
                } else {
                    val indice = items.size + 1
                    setItems(items + "Elemento destacado $indice")
                }
            }
        )

        Text(
            text = if (extendedActive) "Estado extendido: ACTIVO" else "Estado extendido: INACTIVO",
            style = MaterialTheme.typography.bodyMedium
        )

        // Ejemplo de FAB y FAB extendido deshabilitados.
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppFab(
                icon = Icons.Rounded.Add,
                contentDescription = "Agregar deshabilitado",
                onClick = {},
                enabled = false
            )
            AppExtendedFab(
                text = "Acción deshabilitada",
                icon = Icons.Rounded.Add,
                onClick = {},
                enabled = false
            )
        }

        if (items.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Elementos creados:", style = MaterialTheme.typography.titleSmall)
            items.forEach { item ->
                Text(text = "- $item", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
