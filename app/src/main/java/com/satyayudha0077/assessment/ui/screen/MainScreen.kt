package com.satyayudha0077.assessment.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.satyayudha0077.assessment.R
import com.satyayudha0077.assessment.model.Buah
import com.satyayudha0077.assessment.ui.theme.AssessmentTheme
import kotlin.collections.forEach

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, data: List<Buah>) {
    val data = listOf(
        Buah(stringResource(R.string.buah_a), R.drawable.apel, R.string.apel),
        Buah(stringResource(R.string.buah_al), R.drawable.alpukat, R.string.alpukat),
        Buah(stringResource(R.string.buah_j), R.drawable.jeruk, R.string.jeruk),
        Buah(stringResource(R.string.buah_jam), R.drawable.jambu, R.string.jambu),
        Buah(stringResource(R.string.buah_p), R.drawable.pisang, R.string.pisang)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            data.forEachIndexed { index, buah ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/$index") }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = buah.gambar),
                        contentDescription = buah.nama,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = buah.nama, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun ScreenContent(
    data: List<Buah>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        data.forEach { buah ->

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // nanti navigation di sini
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = buah.gambar),
                    contentDescription = buah.nama,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = buah.nama,
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    val dummyData = listOf(
        Buah("Apel", R.drawable.apel, R.string.apel),
        Buah("Pisang", R.drawable.pisang, R.string.pisang)
    )
    AssessmentTheme {
        MainScreen(navController, dummyData)
    }
}
