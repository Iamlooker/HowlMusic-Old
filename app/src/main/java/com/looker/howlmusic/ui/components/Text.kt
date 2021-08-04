package com.looker.howlmusic.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.looker.howlmusic.ui.theme.Typography

@Composable
fun HeaderText(
    modifier: Modifier = Modifier,
    text: String?,
    style: TextStyle = Typography.body1,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier.padding(horizontal = 8.dp),
        text = text ?: "No Title",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = style,
        color = MaterialTheme.colors.onBackground,
        textAlign = textAlign
    )
}

@Composable
fun BodyText(
    modifier: Modifier = Modifier,
    text: String?,
    style: TextStyle = Typography.body2,
) {
    Text(
        modifier = modifier.padding(horizontal = 8.dp),
        text = text ?: "No Artist",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = style,
        color = MaterialTheme.colors.onBackground
    )
}