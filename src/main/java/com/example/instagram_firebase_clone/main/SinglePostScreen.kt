package com.example.instagram_firebase_clone.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.instagram_firebase_clone.DestinationScreen
import com.example.instagram_firebase_clone.IgViewModel
import com.example.instagram_firebase_clone.auth.CommonDivider
import com.example.instagram_firebase_clone.data.PostData

@Composable
fun SinglePostScreen(navController: NavController, vm: IgViewModel, post: PostData) {
    post.userId?.let {
        Column(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(
                text = "Back",
                modifier = Modifier.clickable {
                    navController.popBackStack()
                }
            )

            CommonDivider()

            SinglePostDisply(navController, vm, post)
        }
    }
}

@Composable
fun SinglePostDisply(navController: NavController, vm: IgViewModel, post: PostData) {

    val userData = vm.userData.value
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier.padding(8.dp)
                    .size(32.dp)
            ) {
                Image(painter = rememberAsyncImagePainter(model = post.userImage), contentDescription = null)
            }

            Text(text = post.username ?: "")
            Text(text = ".", modifier = Modifier.padding(8.dp))

            if (userData?.userId == post.userId) {
                // view own post. Don't show anything
            } else {
                Text(text = "Follow", color = Color.Blue, modifier = Modifier.clickable {
                    // TODO: Follow a user
                })
            }
        }
    }

    // for post image
    Box {
        val modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 150.dp)
        CommonImage(data = post.postImage, modifier = modifier, contentScale = ContentScale.FillWidth)
    }

    // for the likes
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(com.example.instagram_firebase_clone.R.drawable.ic_like),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.Red)
        )

        Text(text = "${post.likes?.size ?: 0} likes", modifier = Modifier.padding(start = 0.dp))
    }

    // for the description
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = post.username ?: "", fontWeight = FontWeight.Bold)
        Text(text = post.postDescription ?: "", modifier = Modifier.padding(start = 8.dp))
    }

    // for my comment
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "10 comments", color = Color.Gray, modifier = Modifier.padding(start = 8.dp))
    }
}