package com.represa.quoty.ui.screen

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.represa.quoty.data.model.network.quote.LoginStatus
import com.represa.quoty.ui.viewmodel.LoginViewModel

@Composable
fun Login(viewModel: LoginViewModel) {

    var user = remember { mutableStateOf("rodrire") }
    var password = remember { mutableStateOf("123456") }

    val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

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
            Row(Modifier.gravity(Alignment.CenterHorizontally).padding(0.dp, 6.dp)) {
                Button(onClick = { drawerState.expand()
                }) {
                     Text(text = "SignUp")}
                }
        }
        progressBar(viewModel.prueba)
    }

    /*BottomDrawerLayout(drawerState = drawerState,
        drawerContent = {
            // add your UI code
        }, bodyContent = {
            // add your UI code
        }
    )*/

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

@Composable
fun SignUp(credential: MutableState<String>, label: String, isSignUp: MutableState<Boolean>) {
    if(isSignUp.value) {
        TextField(
            value = credential.value,
            onValueChange = { credential.value = it },
            label = { Text(text = label) }
        )
    }
}
