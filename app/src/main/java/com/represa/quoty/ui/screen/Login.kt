package com.represa.quoty.ui.screen

import android.widget.ProgressBar
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.gravity
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.represa.quoty.data.Repository
import com.represa.quoty.data.model.LoginStatus
import com.represa.quoty.ui.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun Login(viewModel: LoginViewModel) {

    var user = remember { mutableStateOf("user") }
    var password = remember { mutableStateOf("password") }

    Stack {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Row(
                Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp, 6.dp)
            ) { LoginTextField(user, "user") }
            Row(
                Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp, 6.dp)
            ) { LoginTextField(password, "pass") }
            Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp, 6.dp)) {
                LoginButton(
                    user,
                    password,
                    viewModel::login
                )
            }
        }
        progressBar(viewModel.prueba)
    }

}

@Composable
fun progressBar(isEnable: LoginStatus) {
    if (isEnable == LoginStatus.LOGIN_LOAD) {
        AlertDialog(
            onDismissRequest = {},
            title = {},
            text = {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()
                }
            },
            confirmButton = {},
            backgroundColor = Color.Transparent
        )
    }
}

@Composable
fun LoginButton(
    user: MutableState<String>,
    password: MutableState<String>,
    onclick: (String, String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    //val viewModel: LoginViewModel by viewModel()

    Button(onClick = { onclick(user.value, password.value) }
    ) {
        Text(text = "Login")
    }
}


@Composable
fun LoginTextField(credential: MutableState<String>, label: String) {
    TextField(
        value = credential.value,
        onValueChange = { credential.value = it },
        label = { Text(text = label) }
    )
}
