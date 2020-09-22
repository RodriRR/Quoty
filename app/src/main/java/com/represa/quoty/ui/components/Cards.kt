package com.represa.quoty.ui.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Preview
@Composable
fun MainCard(){
    Card(Modifier.fillMaxWidth().preferredHeight(50.dp).background(Color.Red)) {
        Column() {
            Text(text = "holaaaaaaa")
        }
    }
}