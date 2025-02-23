package com.example.assignment_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_2.ui.theme.Assignment_2Theme

data class Slides(
    val imageCount: Int,
    val title: String,
    val artist: String,
    val year: String
)

val artworks = listOf(
    Slides(R.drawable.mona_lisa_50_jpg, "Mona Lisa", "Leonardo Da Vinci", "(1503-19)"),
    Slides(R.drawable.the_creation_of_adam_jpg, "The Creation of Adam", "Michaelangelo", "(1511)"),
    Slides(R.drawable.whistlers_mother_jpg, "Whistler's Mother", "James Abbott McNeill Whistler", "(1871)")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Artwork(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Artwork(modifier: Modifier = Modifier) {
    val page = remember { mutableStateOf(0) }
    val fullArtwork = artworks[page.value]

    Column(
        modifier = modifier.fillMaxSize().padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column (
            modifier = modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Surface(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Display(fullArtwork)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            color = Color(0xFFE3EFF4)
        ) {
            Description(fullArtwork)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Navigation(
            currentPage = page.value,
            onPageChange = { page.value = it }
        )
    }
}

@Composable
fun Display(artwork: Slides) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Image(
            //imageCount holds the resource id of the images, now being passed to the painter to access the images
            painter = painterResource(id = artwork.imageCount),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(420.dp).padding(16.dp),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
fun Description(artwork: Slides) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = artwork.title, textAlign = TextAlign.Start, fontSize = 20.sp)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = artwork.artist, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = artwork.year, fontWeight = FontWeight.Light, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Navigation(currentPage: Int, onPageChange: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(
            onClick = { if (currentPage > 0){
                onPageChange(currentPage - 1)
            }
                        else{
                            onPageChange(artworks.size - 1)
                        }
                      },
            modifier = Modifier.width(120.dp),
        ) {
            Text("Previous")
        }

        Button(
            onClick = { if
                    (currentPage < artworks.size - 1){
                onPageChange(currentPage + 1)
                    }
                        else{
                        onPageChange(0)
                            }
                      },
            modifier = Modifier.width(120.dp),
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtworkPreview() {
    Assignment_2Theme {
        Artwork()
    }
}
