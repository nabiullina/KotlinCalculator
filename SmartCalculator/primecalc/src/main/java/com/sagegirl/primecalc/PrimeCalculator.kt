package com.sagegirl.primecalc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.sagegirl.logger.Logger
import kotlin.math.sqrt

@Composable
fun StartPrimeCalculator() {

    Text(text = "Enter the number:", Modifier.padding(10.dp))
    var number by remember {
        mutableIntStateOf(0)
    }

    OutlinedTextField(
        value = number.toString(),
        onValueChange = { number = it.toInt() },
        label = { Text("Temperature (in Celsius)") }
    )

    if (number <= 0) {
        Logger.warning("The number should be positive value : $number")
    } else {
        Logger.info("The number $number is prime: ${isPrime(number)}")
        Text(text = "The number $number is prime: ${isPrime(number)}", Modifier.padding(10.dp))
    }
}

fun isPrime(number: Int): Boolean {
    if ((number == 0) || (number == 1)) {
        return false;
    }

    var result = true

    for (i in 2..sqrt(number.toDouble()).toInt()) {
        if (number % i == 0) {
            result = false
            break
        }
    }

    return result
}
