package com.sagegirl.circles

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sagegirl.logger.Logger
import kotlin.math.*

@Composable
fun StartCirclesCalculator() {
    var x1: Double by remember { mutableDoubleStateOf(0.0) }
    var x2: Double by remember { mutableDoubleStateOf(0.0) }
    var y1: Double by remember { mutableDoubleStateOf(0.0) }
    var y2: Double by remember { mutableDoubleStateOf(0.0) }
    var r1: Double by remember { mutableDoubleStateOf(0.0) }
    var r2: Double by remember { mutableDoubleStateOf(0.0) }
    var result: String by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Simple circles calculator")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = x1.toString(),
            onValueChange = { x1 = it.toDouble() },
            label = { Text("x1") }
        )

        OutlinedTextField(
            value = y1.toString(),
            onValueChange = { y1 = it.toDouble() },
            label = { Text("y1") }
        )

        OutlinedTextField(
            value = x2.toString(),
            onValueChange = { x2 = it.toDouble() },
            label = { Text("x2") }
        )

        OutlinedTextField(
            value = y2.toString(),
            onValueChange = { y2 = it.toDouble() },
            label = { Text("y2") }
        )

        OutlinedTextField(
            value = r1.toString(),
            onValueChange = { r1 = it.toDouble() },
            label = { Text("r1") }
        )

        OutlinedTextField(
            value = r2.toString(),
            onValueChange = { r2 = it.toDouble() },
            label = { Text("r2") }
        )

        Button(
            onClick = { result = checkCircles(x1, y1, r1, x2, y2, r2).name },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
        ) {
            Text(text = "Calculate")
        }

        Spacer(modifier = Modifier)

        Text(text = "Two circles: $result")
        Logger.info("Result is $result")

    }
}

fun checkCircles(
    x1: Double,
    y1: Double,
    r1: Double,
    x2: Double,
    y2: Double,
    r2: Double
): ResultCircles {
    val distance = sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0))
    if (r1 == r2 && x1 == x2 && y1 == y2) {
        return ResultCircles.COINCIDE
    } else if (distance < r1 + r2 && distance > abs(r1 - r2)) {
        return ResultCircles.INTERSECT
    } else if (distance > r1 + r2) {
        return ResultCircles.DO_NOT_INTERSECT
    } else if (distance == r1 + r2) {
        return ResultCircles.TOUCH
    } else if (distance < r1 + r2) {
        return ResultCircles.INSIDE
    }

    return ResultCircles.ERROR
}

enum class ResultCircles {

    COINCIDE,
    INTERSECT,
    DO_NOT_INTERSECT,
    TOUCH,
    INSIDE,
    ERROR
}
