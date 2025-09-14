package com.vaib.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaib.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember{mutableStateOf("")}
    var outputValue by remember{mutableStateOf("")}
    var inputUnit by remember {mutableStateOf("Meters")}
    var outputUnit by remember{mutableStateOf("Meters")}
    var iExpanded by remember{mutableStateOf(false)}
    var oExpanded by remember{mutableStateOf(false)}
    var conversionFactor = remember{mutableStateOf(1.00)}
    var oConversionFactor = remember{mutableStateOf(1.00)}

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0  / oConversionFactor.value).roundToInt() /100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //ui element will be stacked vertically
        Text("Unit Conoverter", style = MaterialTheme.typography.headlineMedium //modifier= Modifier.padding(100.dp)
             )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value =inputValue,
            onValueChange = {
                inputValue = it //here goes what should happen, when value of outline field changes
             },
            label = {Text("Enter the value")}
            )
        Spacer(modifier = Modifier.height(16.dp))
        Row {//
//            val context = LocalContext.current
//            // here ui will be stacked sequentially
//           Button(onClick = { Toast.makeText(context, "thanks for clicjing",
//               Toast.LENGTH_LONG).show() }) {
//               Text("Click me!")
//           }
            //input box
            Box{
                //input button
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription="ArrowDoewned")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iExpanded =false
                        inputValue="Centimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                    })

                    DropdownMenuItem(text = { Text("Meter") }, onClick = { iExpanded =false
                        inputValue="Feet"
                        conversionFactor.value = 1.0
                        convertUnits() })
                    DropdownMenuItem(text = { Text("feet") }, onClick = { iExpanded =false
                        inputValue="Centimeters"
                        conversionFactor.value = 0.3408
                        convertUnits() })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = { iExpanded =false
                        inputValue="MilliMeters"
                        conversionFactor.value = 0.001
                        convertUnits() })
                }


            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "ArrowDoewned")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oExpanded =false
                        outputUnit="centieters"
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeter") }, onClick = {
                        oExpanded =false
                        outputUnit="MilliMeters"
                        oConversionFactor.value = 0.001
                        convertUnits() })
                    DropdownMenuItem(text = { Text("Meter") }, onClick = {oExpanded =false
                        outputUnit="Meters"
                        oConversionFactor.value = 1.00
                        convertUnits()})
                    DropdownMenuItem(text = { Text("feet") }, onClick = { oExpanded =false
                        outputUnit="feet"
                        oConversionFactor.value = 0.3408
                        convertUnits() })
                }

            }
            }
        Spacer(modifier = Modifier.height(16.dp))
        Text("result : $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
        }



}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
     UnitConverter()
}
