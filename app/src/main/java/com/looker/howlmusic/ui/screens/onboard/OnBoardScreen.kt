package com.looker.howlmusic.ui.screens.onboard

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.howlmusic.R
import com.looker.howlmusic.ui.composables.ImageCompMain
import com.looker.howlmusic.viewModels.HowlViewModel

@Composable
fun OnBoardingPage(
    viewModel: HowlViewModel = viewModel(),
) {

    val color = viewModel.buttonColor.value

    val bannerText = AnnotatedString(
        "Whoops",
        SpanStyle(
            MaterialTheme.colors.onBackground,
            fontSize = 24.sp
        )
    ) + AnnotatedString(
        " Nothing ",
        SpanStyle(
            colorResource(id = color),
            fontSize = 24.sp
        )
    ) + AnnotatedString(
        "Here",
        SpanStyle(
            MaterialTheme.colors.onBackground,
            fontSize = 24.sp
        )
    )

    val text = viewModel.buttonText.value

    val icon = viewModel.buttonIcon.value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                viewModel.buttonText.value = "Granted"
                viewModel.buttonIcon.value = Icons.Default.Done
                viewModel.buttonColor.value = R.color.green
            }
        }
    )

    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageCompMain(
                painter = painterResource(id = R.drawable.empty)
            )

            Text(
                text = bannerText,
                textAlign = TextAlign.Center
            )

            UpdatingButton(
                buttonText = text,
                buttonIcon = icon,
                buttonColor = color
            ) {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    -> {
                        viewModel.buttonText.value = "Granted"
                        viewModel.buttonIcon.value = Icons.Default.Done
                        viewModel.buttonColor.value = R.color.green
                    }
                    else -> {
                        viewModel.buttonText.value = "Permission Denied"
                        launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }
        }
    }
}

@Composable
fun UpdatingButton(
    buttonText: String,
    buttonIcon: ImageVector,
    @ColorRes buttonColor: Int,
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
                contentDescription = "permissionState",
                tint = colorResource(
                    id = buttonColor
                )
            )
            Text(
                text = AnnotatedString(
                    text = buttonText,
                    SpanStyle(color = colorResource(id = buttonColor))
                ),
                modifier = Modifier.animateContentSize()
            )
        }
    }
}