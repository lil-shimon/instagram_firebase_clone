package com.example.instagram_firebase_clone.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import com.example.instagram_firebase_clone.IgViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.instagram_firebase_clone.auth.CommonDivider

/**
 * New post screen
 *
 * @param navController
 * @param vm
 * @param encodedUri image uri
 */
@Composable
fun NewPostScreen(navController: NavController, vm: IgViewModel, encodedUri: String) {
    val imageUri by remember { mutableStateOf(encodedUri) }
    var description by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cancel",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )
            Text(
                text = "Post",
                modifier = Modifier.clickable {
                    focusManager.clearFocus()
                    vm.onNewPost(Uri.parse(imageUri), description) {
                        navController.popBackStack()
                    }
                }
            )
        }

        CommonDivider()

        Image(
            painter = rememberImagePainter(
                imageUri
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp),
            contentScale = ContentScale.FillWidth
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                label = { Text(text = "Description") },
                singleLine = false,
                colors = TextFieldDefaults
                    .textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black
                    )
            )
        }
    }

    val isLoading = vm.inProgress.value
    if (isLoading)
        CommonProgressSpinner()
}