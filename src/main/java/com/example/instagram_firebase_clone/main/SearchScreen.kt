package com.example.instagram_firebase_clone.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.instagram_firebase_clone.IgViewModel

@Composable
fun SearchScreen(navController: NavController, vm: IgViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(text = "Search Screen")
        }
        BottomNavigationMain(
            selectedItem = BottomNavigationItem.SEARCH,
            navController = navController
        )
    }
}