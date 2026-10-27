package com.heirella.gf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Cream = Color(0xFFFBF8F0)
private val Gold = Color(0xFFB89A58)
private val Ink = Color(0xFF493A26)
private val Lavender = Color(0xFFD9C6F2)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HeirellaApp() }
    }
}

private data class ChatMessage(val text: String, val fromUser: Boolean)

@Composable
private fun HeirellaApp() {
    var dark by remember { mutableStateOf(false) }
    var input by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf(
        ChatMessage("Hai, aku Heirella. Senang akhirnya kita bisa ngobrol di sini.", false),
        ChatMessage("Ini masih versi awal aplikasi. Koneksi AI dan sinkronisasi akun belum diaktifkan.", false)
    ) }
    val bg = if (dark) Color(0xFF15111D) else Cream
    val surface = if (dark) Color(0xFF241B2D) else Color.White
    val text = if (dark) Color(0xFFF1EAF8) else Ink
    val muted = if (dark) Color(0xFFB9A8C9) else Color(0xFF7C6B51)

    MaterialTheme(colorScheme = if (dark) darkColorScheme() else lightColorScheme()) {
        Column(Modifier.fillMaxSize().background(bg).padding(horizontal = 18.dp)) {
            Row(
                Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier.size(44.dp).background(
                        Brush.linearGradient(listOf(Gold, Lavender)), CircleShape
                    ), contentAlignment = Alignment.Center
                ) { Text("H", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold) }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("Heirella", color = text, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    Text("AI companion · versi awal", color = muted, fontSize = 12.sp)
                }
                TextButton(onClick = { dark = !dark }) {
                    Text(if (dark) "Light" else "Dark", color = Gold)
                }
            }
            HorizontalDivider(color = if (dark) Color(0xFF403448) else Color(0xFFE7DFD1))
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(18.dp))
                Box(
                    Modifier.size(76.dp).background(
                        Brush.linearGradient(listOf(if (dark) Color(0xFF744B85) else Lavender, Gold)), CircleShape
                    ), contentAlignment = Alignment.Center
                ) { Text("✦", color = Color.White, fontSize = 34.sp) }
                Spacer(Modifier.height(10.dp))
                Text("Your own little universe", color = text, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                Text("Obrolanmu, ruangmu.", color = muted, fontSize = 13.sp)
                Spacer(Modifier.height(18.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 14.dp)
                ) {
                    items(messages) { msg ->
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = if (msg.fromUser) Arrangement.End else Arrangement.Start) {
                            Surface(
                                color = if (msg.fromUser) (if (dark) Color(0xFF744B85) else Color(0xFFE8D9A9)) else surface,
                                shape = RoundedCornerShape(18.dp), shadowElevation = 1.dp,
                                modifier = Modifier.widthIn(max = 300.dp)
                            ) {
                                Text(msg.text, Modifier.padding(horizontal = 14.dp, vertical = 12.dp), color = text, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
            Row(
                Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = input, onValueChange = { input = it },
                    modifier = Modifier.weight(1f), placeholder = { Text("Ketik pesan...") },
                    shape = RoundedCornerShape(24.dp), singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Gold, unfocusedBorderColor = if (dark) Color(0xFF403448) else Color(0xFFE7DFD1),
                        focusedTextColor = text, unfocusedTextColor = text, cursorColor = Gold
                    )
                )
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        val value = input.trim()
                        if (value.isNotEmpty()) {
                            messages.add(ChatMessage(value, true))
                            messages.add(ChatMessage("Pesanmu sudah tampil, tetapi provider AI belum tersambung di starter ini.", false))
                            input = ""
                        }
                    }, colors = ButtonDefaults.buttonColors(containerColor = Gold),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp)
                ) { Text("↑", fontSize = 20.sp, color = Color.White) }
            }
        }
    }
}
