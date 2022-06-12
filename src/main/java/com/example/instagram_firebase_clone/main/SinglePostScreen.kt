package com.example.instagram_firebase_clone.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.instagram_firebase_clone.IgViewModel
import com.example.instagram_firebase_clone.data.PostData

@Composable
fun SinglePostScreen(navController: NavController, vm: IgViewModel, post: PostData) {
    Text(
        text = "Single Post Screen ${post.postDescription}"
    )
}