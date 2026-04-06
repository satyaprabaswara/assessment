package com.satyayudha0077.assessment.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.satyayudha0077.assessment.R
import com.satyayudha0077.assessment.model.Buah


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(buah: Buah, navController: NavController) {
    val context = LocalContext.current
    var jumlah by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(buah.nama) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.kembali))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = buah.gambar),
                contentDescription = buah.nama,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(buah.nama, fontSize = 26.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(stringResource(id = buah.deskripsi))

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = jumlah,
                onValueChange = { jumlah = it },
                label = { Text(stringResource(R.string.jumlah_label)) }
            )

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = harga,
                onValueChange = { harga = it },
                label = { Text(stringResource(R.string.harga_label)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                val jml = jumlah.toIntOrNull()
                val hrg = harga.toIntOrNull()

                result = if (jml != null && hrg != null) {
                    "Total harga: Rp. ${jml * hrg}"
                } else {
                    context.getString(R.string.input_invalid)
                }
            }) {
                Text(stringResource(R.string.hitung_total))
            }

            Spacer(modifier = Modifier.height(16.dp))
            if (result.isNotEmpty()) {
                Text(result, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, result)
                    }
                    context.startActivity(Intent.createChooser(intent, "Bagikan hasil"))
                }) {
                    Text(stringResource(R.string.bagikan))
                }
            }
        }
    }
}

