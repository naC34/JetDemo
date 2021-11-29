package com.nac.module_main

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nac.module_common.db.entity.Jet

@Composable
fun MainPage(content: String, localContent: List<Jet>, remoteGet: () -> Unit, save: () -> Unit) {
    Scaffold {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            MainOperate(remoteGet = remoteGet, save = save)
            Spacer(modifier = Modifier.height(20.dp))
            MainContent(content = content)
            Spacer(modifier = Modifier.height(20.dp))
            MainLocalContent(localContent = localContent)
        }
    }
}

@Composable
fun MainContent(content: String) {
    Text(
        modifier = Modifier.padding(bottom = 20.dp),
        text = content,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MainLocalContent(localContent: List<Jet>) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        localContent.forEach { content ->
            MainContent(content = content.data)
        }
    }
}

@Composable
fun MainOperate(remoteGet: () -> Unit, save: () -> Unit) {
    Row {
        Button(onClick = remoteGet) {
            Text(text = "get remote")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = save) {
            Text(text = "save local")
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MainPage(content = "main content", localContent = listOf(), remoteGet = {}, save = {})
}