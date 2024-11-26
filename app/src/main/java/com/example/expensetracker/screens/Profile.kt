package com.example.expensetracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.R

@Composable
fun Profile(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFFFE0BD).copy(0.5f))
            .padding(top = 50.dp)
    ) {
        ProfileData()
        ProfileOptions()
    }
}

@Composable
fun ProfileOptions() {
    ElevatedCard(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 16.dp),
        ) {
            ProfileOptionItem(R.drawable.baseline_account_balance_wallet_24, "Account")
            ProfileOptionItem(R.drawable.baseline_settings_24, "Settings")
            ProfileOptionItem(R.drawable.baseline_import_export_24, "Export Data")
            ProfileOptionItem(R.drawable.baseline_logout_24, "Logout")
//            ProfileOptionItem(Icons.Default.AccountCircle,"Account")
//            ProfileOptionItem(Icons.Default.AccountCircle,"Account")
        }
    }
}

@Composable
fun ProfileOptionItem(icon: Int, title: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF7E60BF).copy(0.2f), shape = RoundedCornerShape(16.dp))
                .padding(8.dp),

        ) {
            Icon(
                painter = painterResource(icon),
                title,
                Modifier.size(50.dp),
                tint = Color(0xFF7E60BF),
            )
        }
        Text(
            title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun ProfileData() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(2.dp, Color(0xFF00BFFF), CircleShape)
        ) {
            Icon(
                painter = painterResource(R.drawable.profile),
                "profile",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .clip(shape = CircleShape),

                tint = Color.Unspecified
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text("Username", fontSize = 22.sp, color = Color.DarkGray.copy(0.6f))
            Text("Mithu Don", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
        Icon(Icons.Rounded.Edit, "EditIcon", modifier = Modifier.size(30.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ProfilePreview() {
    Profile(rememberNavController())
}
