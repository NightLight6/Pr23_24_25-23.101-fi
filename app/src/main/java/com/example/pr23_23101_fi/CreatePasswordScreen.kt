package com.example.pr23_23101_fi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CreatePasswordScreen(
    onNavigateToMain: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pin by remember { mutableStateOf("") }
    val maxPinLength = 4
    val isPinComplete = pin.length == maxPinLength

    LaunchedEffect(pin) {
        if (isPinComplete) {
            delay(400)
            onNavigateToMain()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Text("Создание пароля", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            repeat(maxPinLength) { index ->
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(if (index < pin.length) Color(0xFF4A90E2) else Color(0xFFE0E0E0))
                )
            }
        }

        Spacer(Modifier.weight(1f))

        val digits = listOf("1","2","3","4","5","6","7","8","9","","0","⌫")
        digits.chunked(3).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                row.forEach { digit ->
                    if (digit.isNotEmpty()) {
                        Button(
                            onClick = {
                                if (digit == "⌫") {
                                    if (pin.isNotEmpty()) pin = pin.dropLast(1)
                                } else if (pin.length < maxPinLength) {
                                    pin += digit
                                }
                            },
                            modifier = Modifier.size(72.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            elevation = ButtonDefaults.buttonElevation(4.dp)
                        ) {
                            Text(digit, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Spacer(Modifier.size(72.dp))
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
        }

        Spacer(Modifier.height(24.dp))
        TextButton(onClick = onNavigateToMain) {
            Text("Пропустить", color = Color(0xFF4A90E2), fontSize = 16.sp)
        }
        Spacer(Modifier.height(24.dp))
    }
}