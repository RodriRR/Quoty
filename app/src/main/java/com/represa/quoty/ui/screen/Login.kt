package com.represa.quoty.ui.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.represa.quoty.data.Repository
import kotlinx.coroutines.launch

@Composable
fun Login(){

    var user = remember {  mutableStateOf("user") }
    var password = remember {  mutableStateOf("password") }

    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginTextField(user, "user") }
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginTextField(password, "pass") }
        Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp,6.dp)) { LoginButton(user,password) }
    }
}

@Composable
fun LoginButton(user : MutableState<String>, password : MutableState<String>) {
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        coroutineScope.launch {
            Repository.login(user.value, password.value)
        }
    } ) {
        Text(text = "Login")
    }
}


@Composable
fun LoginTextField(credential : MutableState<String>, label : String){
    TextField(
            value = credential.value,
            onValueChange = { credential.value = it },
            label = { Text(text = label) }
    )
}
