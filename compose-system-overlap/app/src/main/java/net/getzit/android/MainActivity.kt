package net.getzit.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent { MaterialTheme { MainUI() } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI() {
    var showModalBottomSheet by rememberSaveable { mutableStateOf(false) }

    Scaffold(Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        topBar = {
            TopAppBar(title = {
                Text(stringResource(R.string.app_name))
            }, navigationIcon = {
                TextButton(onClick = { }) {
                    Text("BUTTON")
                }
            })
        }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            TextButton(onClick = { showModalBottomSheet = true }) {
                Text("show modal bottom sheet")
            }
        }
    }

    if (showModalBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showModalBottomSheet = false }) {
            // must be inside. the insets retrieved here are not the same as outside!
            val paddingValues = WindowInsets.safeDrawing.asPaddingValues()
            Box(
                Modifier
                    .absolutePadding(
                        bottom = paddingValues.calculateBottomPadding(),
                        left = paddingValues.calculateLeftPadding(LayoutDirection.Ltr),
                        right = paddingValues.calculateRightPadding(LayoutDirection.Ltr)
                    )
                    .fillMaxWidth(),
            ) {
                Column(
                    Modifier
                        .background(Color.Cyan)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextButton(onClick = { }) {
                        Text("hope this is clickable")
                    }
                }
            }
        }
    }
}
