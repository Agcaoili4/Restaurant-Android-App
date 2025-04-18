package com.example.inventory.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.RowScope
import com.example.inventory.ui.theme.*


//Added
@Composable
fun AppButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    val isDarkTheme = MaterialTheme.colorScheme.background == md_theme_dark_background
    val buttonColor = if (isDarkTheme) md_theme_dark_button else md_theme_light_button
    val onButtonColor = if (isDarkTheme) md_theme_dark_onButton else md_theme_light_onButton

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = onButtonColor
        ),
        content = content
    )
}
