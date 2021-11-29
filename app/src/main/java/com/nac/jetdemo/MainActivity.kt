package com.nac.jetdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.nac.module_main.MainPage
import com.nac.module_main.MainViewModel
import com.nac.jetdemo.ui.theme.JetDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetDemoTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val localContent = viewModel.localData.collectAsState(initial = listOf())

    MainPage(
        content = viewModel.data.value,
        localContent = localContent.value,
        remoteGet = { viewModel.getData() },
        save = { viewModel.saveLocal() },
    )
}