package com.example.jetpackcompose

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CA2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Enroll()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Enroll() {

    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var showDetails by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    if (showDetails) {
        DetailScreen(name, code, duration)
    } else {

        Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) })
        { padding ->

            Column(
                modifier = Modifier
                    .padding(padding).padding(top=100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Course Name") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text("Course Code") }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Course Duration") }
                )

                Spacer(modifier = Modifier.padding(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it }
                    )
                    Text("Confirm Enrollment")
                }

                Button(onClick = {
                    val d = duration.toInt()

                    if (name.isNotEmpty() && code.isNotEmpty() &&
                        duration.isNotEmpty() && d > 0 && isChecked
                    ) {
                        showDetails = true
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Please enter valid course details and accept the guidelines."
                            )
                        }
                    }
                }) {
                    Text("Enroll")
                }
            }
        }
    }
}

@Composable
fun DetailScreen(name: String, code: String, duration: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Enrollment Successful", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Course Name: $name")
        Text("Course Code: $code")
        Text("Course Duration: $duration weeks")
    }
}



