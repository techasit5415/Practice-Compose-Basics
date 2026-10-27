package com.example.practice_compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practice_compose_basics.ui.theme.PracticeComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeComposeBasicsTheme {
                PracticeComposeBasicsApp()
            }
        }
    }
}

@Composable
fun PracticeComposeBasicsApp() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabTitles = listOf("Article", "Task Manager", "Quadrant")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        val contentModifier = Modifier.padding(innerPadding)
        when (selectedTabIndex) {
            0 -> ComposeArticleApp(modifier = contentModifier)
            1 -> TaskCompletedScreen(modifier = contentModifier)
            2 -> ComposeQuadrantApp(modifier = contentModifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PracticeComposeBasicsAppPreview() {
    PracticeComposeBasicsTheme {
        PracticeComposeBasicsApp()
    }
}
