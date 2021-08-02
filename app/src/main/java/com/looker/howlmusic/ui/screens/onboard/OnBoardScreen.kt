package com.looker.howlmusic.ui.screens.onboard

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.howlmusic.R
import com.looker.howlmusic.viewModels.HowlViewModel

@Composable
fun OnBoardingPage(
    viewModel: HowlViewModel = viewModel(),
) {

    val text = viewModel.text.value

    val icon = viewModel.icon.value

    val color = viewModel.color.value

    val enabled = viewModel.enabled.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if (it) {
                viewModel.enabled.value = !it
                viewModel.text.value = "Granted"
                viewModel.icon.value = Icons.Default.Done
                viewModel.color.value = R.color.green
            }
        }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.empty),
                contentDescription = null
            )

            Text(
                text = AnnotatedString(
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
                ),
                textAlign = TextAlign.Center
            )

            val context = LocalContext.current

            OutlinedButton(
                onClick = {
                    when (PackageManager.PERMISSION_GRANTED) {
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ),
                        -> {
                            viewModel.enabled.value = false
                            viewModel.text.value = "Granted"
                            viewModel.icon.value = Icons.Default.Done
                            viewModel.color.value = R.color.green
                        }
                        else -> {
                            viewModel.text.value = "Permission Denied"
                            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    }
                },
                enabled = enabled
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "permissionState",
                        tint = colorResource(
                            id = color
                        )
                    )
                    Text(
                        text = AnnotatedString(
                            text = text,
                            SpanStyle(color = colorResource(id = color))
                        ),
                        modifier = Modifier.animateContentSize()
                    )
                }
            }
        }
    }
}