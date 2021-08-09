package com.looker.howlmusic.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import com.looker.howlmusic.R

@Composable
fun Up(iconTint: Color = MaterialTheme.colors.primary, upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .size(36.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = iconTint,
            contentDescription = stringResource(R.string.label_back)
        )
    }
}

@Composable
fun ButtonWithIcon(
    buttonText: String,
    buttonIcon: ImageVector,
    buttonColor: Color,
    onClick: () -> Unit,
) {

    OutlinedButton(
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = buttonIcon,
                contentDescription = stringResource(R.string.button_icon_label),
                tint = buttonColor
            )
            Text(
                text = AnnotatedString(
                    text = buttonText,
                    SpanStyle(color = buttonColor)
                ),
                modifier = Modifier.animateContentSize()
            )
        }
    }
}

@Composable
fun PlayButton() {
    IconButton(
        onClick = { },
        modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colors.primary,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = null
        )
    }
}

@Composable
fun NextButton() {

    IconButton(
        onClick = { },
        modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.SkipNext,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )
    }
}

@Composable
fun PrevButton() {

    IconButton(
        onClick = { },
        modifier = Modifier
            .size(30.dp)
            .background(
                color = MaterialTheme.colors.surface,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.SkipPrevious,
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )
    }
}