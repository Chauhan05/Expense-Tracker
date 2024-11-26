package com.example.expensetracker.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensetracker.R

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SetImage()
        TextComponent()
    }
}

@Composable
fun TextComponent(modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(100.dp))
    Text(
        "Simple solution for\nyour budget",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(
        "Counter and distribute the income\n correctly",
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        modifier = Modifier.fillMaxWidth()
    )
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
        Text(
            "Continue",
        )
    }
}

@Composable
fun SetImage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
    ) {
        Image(
            painter = painterResource(R.drawable.rectangle),
            "money",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}