package org.purchases.best.ui.screens.home

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.purchases.best.R
import org.purchases.best.model.screens.home.HomeScreenContract
import org.purchases.best.ui.navigation.NavigationItem
import org.purchases.best.ui.screens.HomeScreenCard
import org.purchases.best.ui.theme.ButtonIconBackgroundColor
import org.purchases.best.ui.theme.ButtonIconColor
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.LocalTitleColor
import org.purchases.best.ui.theme.PrimaryButtonColor
import org.purchases.best.ui.theme.ScreenBackgroundColor

@SuppressLint("RestrictedApi")
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    navController: NavController
) {
    var initialEventSent by remember { mutableStateOf(false) }
    if (initialEventSent.not()) {
        initialEventSent = true
        viewModel.handleEvent(HomeScreenContract.Event.RequestListsEvent)
    }
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
            if (viewModel.screenState.loadingLists) {
                // MainProgressIndicator(modifier = Modifier.weight(1F))
            } else {
                if (viewModel.screenState.lists.isNotEmpty()) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(vertical = 10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        viewModel.screenState.lists.forEach {
                            item {
                                HomeScreenCard(
                                    list = it,
                                    navController = navController,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "Пока нет списков",
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                        )
                    }
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
                    onClick = {
                        val destination =
                            navController.findDestination(NavigationItem.CreateListScreen.route)
                        destination?.let { destinationNotNull ->
                            navController.navigate(destinationNotNull.id)
                        }
                    }
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
