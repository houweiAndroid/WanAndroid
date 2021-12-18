package com.vesync.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class ComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                MessageCard(Message("Android", "Jetpack"))
            }
        }
    }


    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(message: Message) {
        Row(
            modifier = Modifier
                .padding(all = 10.dp)
                .background(color = Color.White)
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = painterResource(R.drawable.ic_dashboard_black_24dp),
                contentDescription = "Contact profile picture",
            )

            Column {
                Text(text = "Hello  ${message.author}")
                Text(text = "Hello  ${message.body}")
                Text(text = "Hello  ${message.body}")
            }
        }

    }

    @Preview(name = "Dark Mode")
    @Composable
    fun DefaultPreviewMessageCard() {
        MessageCard(Message("Android", "Jetpack"))
    }
}