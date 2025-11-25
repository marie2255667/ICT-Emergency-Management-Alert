package com.example.ictemergencymangementmodelict_emm

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToMessages: () -> Unit) {
    var webView: WebView? by remember { mutableStateOf(null) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Using the native Android WebView for maximum reliability
                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                webViewClient = WebViewClient() // Keeps navigation inside the WebView
                                webChromeClient = WebChromeClient() // Enables advanced features like video and animations
                                settings.javaScriptEnabled = true
                                settings.domStorageEnabled = true
                                settings.mediaPlaybackRequiresUserGesture = false
                            }.also {
                                webView = it
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = { webView?.loadUrl("https://www.pagasa.dost.gov.ph/") }) {
                        Text("Load PAGASA Site")
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = { Text("Assistance Request") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(onClick = { /* TODO: Add alert action */ }) {
                            Text("Alert")
                        }
                        Button(onClick = { /* TODO: Add rescue action */ }) {
                            Text("Rescue")
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            NewsSection()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Button(onClick = onNavigateToMessages) {
                Text("Create Message")
            }
        }
    }
}

@Composable
fun NewsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("News", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            NewsCard("Barangay Update", modifier = Modifier.weight(1f))
            NewsCard("Municipal Update", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            NewsCard("Provincial Update", modifier = Modifier.weight(1f))
            NewsCard("Philippine News Update", modifier = Modifier.weight(1f))
        }
    }
}

// Assuming NewsCard is defined somewhere else in your project
@Composable
fun NewsCard(title: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
