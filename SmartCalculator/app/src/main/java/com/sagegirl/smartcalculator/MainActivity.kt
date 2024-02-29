package com.sagegirl.smartcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagegirl.circles.StartCirclesCalculator
import com.sagegirl.primecalc.StartPrimeCalculator
import com.sagegirl.smartcalculator.ui.theme.SmartCalculatorTheme
import com.sagegirl.thermometer.StartThermoCalculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(10.dp)
                    ) {
                        composable(route = "home") {
                            Column {
                                TextButton(onClick = { navController.navigate("circles") }) {
                                    Text(text = "Go to Circles Calc")
                                }
                                TextButton(onClick = { navController.navigate("thermo") }) {
                                    Text(text = "Go to Thermo Calc")
                                }
                                TextButton(onClick = { navController.navigate("prime") }) {
                                    Text(text = "Go to Prime Calc")
                                }
                            }
                        }
                        composable(route = "circles") {
                            Column {
                                TextButton(onClick = { navController.navigate("home") }) {
                                    Text(text = "Go Home")
                                }
                                StartCirclesCalculator()
                            }
                        }
                        composable(route = "prime") {
                            Column {
                                TextButton(onClick = { navController.navigate("home") }) {
                                    Text(text = "Go Home")
                                }
                                StartPrimeCalculator()
                            }
                        }
                        composable(route = "thermo") {
                            Column {
                                TextButton(onClick = { navController.navigate("home") }) {
                                    Text(text = "Go Home")
                                }
                                StartThermoCalculator()
                            }
                        }
                    }
                }
            }
        }
    }
}