package org.purchases.best.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.purchases.best.R
import org.purchases.best.ui.theme.ButtonIconBackgroundColor
import org.purchases.best.ui.theme.ButtonIconColor
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.LocalTitleColor
import org.purchases.best.ui.theme.PrimaryButtonColor
import org.purchases.best.ui.theme.ScreenBackgroundColor

@Preview
@Composable
fun HomeScreen() {
    Surface(
        color = ScreenBackgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Список покупок",
                color = LocalTitleColor,
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(vertical = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(30) {
                    HomeScreenCard(title = "Список №${it + 1}")
                }
            }
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 40.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryButtonColor,
                        contentColor = ButtonTextColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 3.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = {}
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_add_24),
                            contentDescription = "drawable_icons",
                            tint = ButtonIconColor,
                            modifier = Modifier
                                .size(30.dp)
                                .background(
                                    color = ButtonIconBackgroundColor,
                                    shape = CircleShape
                                )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = "Новый список",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
