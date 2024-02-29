package com.sagegirl.thermometer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.sagegirl.logger.Logger

class ThermoCalculator {
    var temp = 0.0
    var minTemp = 0.0
    var maxTemp = 0.0


    private fun convertCelsiusToOther(temperature: Double, scale: String): Double {
        return when (scale) {
            "Fahrenheit" -> temperature * 9 / 5 + 32
            "Kelvin" -> temperature + 273.15
            else -> temperature
        }
    }


    @Composable
    fun CheckTemperature(temperature: Double, scale: String, season: String) {
        temp = convertCelsiusToOther(temperature, scale)

        when (season) {
            "Summer" -> {
                minTemp = convertCelsiusToOther(minSummerTemperature, scale)
                maxTemp = convertCelsiusToOther(maxSummerTemperature, scale)
            }

            "Winter" -> {
                minTemp = convertCelsiusToOther(minWinterTemperature, scale)
                maxTemp = convertCelsiusToOther(maxWinterTemperature, scale)
            }
        }

        Column {
            Text(text = "The temperature is $temp˚${scale[0]}", Modifier.padding(10.dp))
            Text(text = "The comfortable temperature is from ${minTemp}˚${scale[0]} to ${maxTemp}˚${scale[0]}", Modifier.padding(10.dp))
            Logger.info("The temperature is $temp˚${scale[0]}")
            Logger.info("The comfortable temperature is from ${minTemp}˚${scale[0]} to ${maxTemp}˚${scale[0]}")

            if (temp < minTemp) {
                Text(text = "Please, make it warmer by ${minTemp - temp} degrees", Modifier.padding(10.dp))
            } else if (temp > maxTemp) {
                Text("Please, make it cooler by ${temp - maxTemp} degrees", Modifier.padding(10.dp))
            }
        }
    }

    companion object {
        const val minSummerTemperature = 22.0
        const val maxSummerTemperature = 25.0
        const val minWinterTemperature = 20.0
        const val maxWinterTemperature = 22.0
    }

}

@Composable
fun StartThermoCalculator() {
    val calc = ThermoCalculator()
    val scales = listOf("Celsius", "Fahrenheit", "Kelvin")
    val (selectedScale, onScaleSelected) = remember { mutableStateOf(scales[0]) }
    Text(text = "Select the scale:")

    Column(Modifier.selectableGroup()) {
        scales.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp), verticalAlignment = Alignment.CenterVertically
            )
            {
                RadioButton(
                    selected = (text == selectedScale),
                    onClick = { onScaleSelected(text) }
                )
                Text(text = text, fontSize = 22.sp)
            }
        }
    }
    Spacer(modifier = Modifier)
    Text(text = "Select the scale:")

    val seasons = listOf("Winter", "Summer")
    val (selectedSeason, onSeasonSelected) = remember { mutableStateOf(seasons[0]) }

    Column(Modifier.selectableGroup()) {
        seasons.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp), verticalAlignment = Alignment.CenterVertically
            )
            {
                RadioButton(
                    selected = (text == selectedSeason),
                    onClick = { onSeasonSelected(text) }
                )
                Text(text = text, fontSize = 22.sp)
            }
        }
    }
    Spacer(modifier = Modifier)
    var temperature: Double by remember { mutableDoubleStateOf(0.0) }
    OutlinedTextField(
        value = temperature.toString(),
        onValueChange = { temperature = it.toDouble() },
        label = { Text("Temperature (in Celsius)") }
    )

    Text(text = String.format("Scale: %s, Season: %s", selectedScale, selectedSeason), Modifier.padding(10.dp))

    var showResult by remember { mutableStateOf(false) }

    Button(
        onClick = {
            showResult = true
        },
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .padding(10.dp)
    ) {
        Text(text = "Calculate")
    }


    if (showResult) {
        calc.CheckTemperature(temperature, selectedScale, selectedSeason)
    }

}
