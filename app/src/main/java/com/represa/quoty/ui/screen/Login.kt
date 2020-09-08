package com.represa.quoty.ui.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Login(){

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginTextField() }
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginTextField() }
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginButton() }
    }
}

@Composable
fun LoginButton() {
    Button(onClick = { } ) {
        Text(text = "Login")
    }
}


@Composable
fun LoginTextField(){
    TextField(
            value = "",
            onValueChange = { },
            label = { Text("") }
    )
}
