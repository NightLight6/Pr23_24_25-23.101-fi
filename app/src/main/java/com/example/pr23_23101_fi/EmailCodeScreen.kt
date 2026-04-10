package com.example.pr23_23101_fi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun EmailCodeScreen(
    onNavigateToCreatePassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    var code by remember { mutableStateOf("") }
    var timeLeft by remember { mutableIntStateOf(60) }
    var timerRunning by remember { mutableStateOf(true) }

    val isCodeValid = code.length == 4 && code.all { char -> char.isDigit() }

    LaunchedEffect(timerRunning) {
        while (timerRunning && timeLeft > 0 && isActive) {
            delay(1000L)
            timeLeft--
        }
        if (timeLeft == 0) {
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Код из Email",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = code,
            onValueChange = { newText: String ->
                val filtered = newText.filter { char -> char.isDigit() }.take(4)
                code = filtered
            },
            label = { Text("Введите 4-значный код") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (timeLeft > 0) {
                "Отправить повторно через: $timeLeft сек"
            } else {
                "Отправить код снова"
            },
            color = if (timeLeft > 0) Color.Gray else Color(0xFF4A90E2)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (isCodeValid) {
                    onNavigateToCreatePassword()
                }
            },
            enabled = isCodeValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isCodeValid) Color(0xFF4A90E2) else Color.Gray
            )
        ) {
            Text("Подтвердить")
        }
    }
}