package com.jetpack.multipleselectitem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.multipleselectitem.ui.theme.MultipleSelectItemTheme
import com.jetpack.multipleselectitem.ui.theme.Purple500

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipleSelectItemTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MultipleSelectItem()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MultipleSelectItem() {
    val selectedItems = rememberSaveable {
        mutableStateOf(setOf<Int>())
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(MaterialTheme.colors.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Multiple Select Item",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(20.dp)
        ) {
            items(30) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, end = 10.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            val checked = selectedItems.value.contains(index)
                            Checkbox(
                                checked = checked,
                                onCheckedChange = {
                                    selectedItems.value = if (checked) {
                                        selectedItems.value - index
                                    } else {
                                        selectedItems.value + index
                                    }
                                },
                                colors = CheckboxDefaults.colors(Purple500)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_default),
                                contentDescription = "Profile Image",
                                modifier = Modifier.size(75.dp)
                            )

                            Text(
                                text = "Name ${index + 1}",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )

                            Text(
                                text = "Department ${index + 1}",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }
                }
            }
        }
    }
}




















