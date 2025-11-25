package com.example.ictemergencymangementmodelict_emm

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onNavigateToPolicy: () -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        uri: Uri? ->
        imageUri = uri
    }

    var completeName by remember { mutableStateOf("") }
    var completeAddress by remember { mutableStateOf("") }
    var barangay by remember { mutableStateOf("") }
    var acceptedPolicy by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Display Photo Box")
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.size(150.dp)) {
                        imageUri?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { launcher.launch("image/*") }) {
                        Text("Upload Photo")
                    }
                }
            }
        }
        item {
            Column {
                OutlinedTextField(
                    value = completeName,
                    onValueChange = { completeName = it },
                    label = { Text("Complete Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = completeAddress,
                    onValueChange = { completeAddress = it },
                    label = { Text("Complete Address") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = barangay,
                    onValueChange = { barangay = it },
                    label = { Text("Barangay") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Column {
                Text(
                    text = "View User Policy",
                    modifier = Modifier.clickable { onNavigateToPolicy() },
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = acceptedPolicy,
                        onCheckedChange = { acceptedPolicy = it }
                    )
                    Text("Accept user policy")
                }
            }
        }
        item {
            Button(onClick = { /*TODO*/ }) {
                Text("Logout")
            }
        }
    }
}
