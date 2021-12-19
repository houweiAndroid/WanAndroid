package com.vesync.wanandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vesync.wanandroid.ui.BasicsCodelabTheme

class ComposeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MessageCard(Message("Android", "Jetpack"))
                }
            }

        }
    }


    data class Message(val author: String, val body: String)


    @Composable
    private fun Greetings(names: List<String> = List(1000) { "$it" }) {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

    @Composable
    private fun Greeting(name: String) {

        val expanded = remember { mutableStateOf(false) }

        val extraPadding = if (expanded.value) 48.dp else 0.dp

        Surface(
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Row(modifier = Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = extraPadding)
                ) {
                    Text(text = "Hello, ")
                    Text(text = name)
                }
                OutlinedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }

            }
        }
    }


    @Composable
    fun MessageCard(message: Message) {

        var expanded = remember { mutableStateOf(false) }
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

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Hello  ${message.author}")
                Text(text = "Hello  ${message.body}")
                Text(text = "Hello  ${message.body}")

                OutlinedButton(
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Text(if (expanded.value) "Show less" else "Show more")
                }

                Greetings()


            }


        }

    }

    @Preview(name = "Dark Mode")
    @Composable
    fun DefaultPreviewMessageCard() {
        MessageCard(Message("Android", "Jetpack"))
    }
}