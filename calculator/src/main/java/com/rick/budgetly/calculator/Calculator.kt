package com.rick.budgetly_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rick.budgetly.calculator.numberAction

private enum class Sinais (val sinal: String){
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICAO("*"),
    DIVISAO("/"),
    EQUAL("=");

    override fun toString(): String {
        return this.sinal
    }
}

enum class Numeros (val numero: String){
    UM("1"),
    DOIS("2"),
    TRES("3"),
    QUATRO("4"),
    CINCO("5"),
    SEIS("6"),
    SETE("7"),
    OITO("8"),
    NOVE("9"),
    PONTO("."),
    ZERO("0"),
    APAGAR("<");

    override fun toString(): String {
        return this.numero
    }
}

@Composable
private fun CalculatorButton(text: String, onclick: () -> Unit) {
    Box(modifier = Modifier
        .padding(2.dp)
        .size(24.dp)
        .wrapContentSize()
        .clickable { onclick() }){
        Text(text = text, style = MaterialTheme.typography.button, fontWeight = FontWeight.Black)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Calculator(numero: String, onclick: (String) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.CenterVertically),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = numero)
        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            for(s in Sinais.values()){
                CalculatorButton(text = s.sinal, onclick = { onclick(s.sinal) })
            }
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ){
            items(Numeros.values()){
                CalculatorButton(text = it.numero, onclick = { onclick(it.numero) })
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
private fun CalculatorCustom() {
    Column(Modifier.fillMaxWidth()) {
        val numero = remember{ mutableStateOf("") }
        Text(text = numero.value, modifier = Modifier.padding(start = 65.dp))
        CustomRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            rows = 1,
            onClick = {}
        ) {
            for (i in Sinais.values()) {
                TextButton(
                    onClick = {
                        numberAction(i.name)
                        if (true){
                        }
                    }, modifier = Modifier
                        .widthIn(min = 65.dp, max = 800.dp)
                ) {
                    Text(text = i.name)
                }
            }
        }
        CustomRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start),
            onClick = {}
        ) {
            for (i in Numeros.values()) {
                TextButton(
                    onClick = {
                        numberAction(i.name)
                    },
                    modifier = Modifier
                        .widthIn(min = 100.dp, max = 800.dp)
                ) {
                    Text(text = i.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun PrevCalculator() {
    Calculator("numero", {})
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun PrevCustomRowCalculator() {
    CalculatorCustom()
}