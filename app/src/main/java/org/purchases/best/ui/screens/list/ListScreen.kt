package org.purchases.best.ui.screens.list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.settings.LIST_ID
import org.purchases.best.ui.navigation.NavigationItem
import org.purchases.best.ui.screens.PurchaseRecord
import org.purchases.best.ui.screens.TopBar
import org.purchases.best.ui.theme.ButtonIconBackgroundColor
import org.purchases.best.ui.theme.ButtonIconColor
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.Dimens
import org.purchases.best.ui.theme.PrimaryButtonColor
import org.purchases.best.ui.theme.ScreenBackgroundColor

@SuppressLint("RestrictedApi")
@Composable
fun ListScreen(
    viewModel: ListScreenViewModel,
    navController: NavController,
    listId: Long
) {
    var requestEventSent by remember { mutableStateOf(false) }
    if (requestEventSent.not()) {
        requestEventSent = true
        viewModel.handleEvent(
            ListScreenContract.Event.RequestListWithPurchases(
                listId = listId
            )
        )
    }
    Scaffold(
        topBar = {
            TopBar(
                listName = viewModel.screenState.data.name,
                navController = navController
            )
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Surface(
                    color = ScreenBackgroundColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TopBar(
                        listName = viewModel.screenState.data.name,
                        navController = navController
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        if (viewModel.screenState.data.purchasesChecked.isEmpty() &&
                            viewModel.screenState.data.purchasesNotChecked.isEmpty()
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(Dimens.Commons.MaxWeight)
                            ) {
                                Text(
                                    text = "Пока нет пунктов",
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 20.dp)
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(Dimens.Commons.MaxWeight)
                            ) {
                                item {
                                    viewModel.screenState.data.purchasesNotChecked.forEach { purchase ->
                                        PurchaseRecord(purchase, viewModel)
                                    }
                                }
                                item {
                                    viewModel.screenState.data.purchasesChecked.forEach { purchase ->
                                        PurchaseRecord(purchase, viewModel)
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(30.dp))
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .padding(bottom = 50.dp)
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
                                    val bundle = Bundle()
                                    bundle.putLong(LIST_ID, listId)
                                    val destination =
                                        navController.findDestination(NavigationItem.AddItemScreen.route)
                                    destination?.let { destinationNotNull ->
                                        navController.navigate(destinationNotNull.id, bundle)
                                    }
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(vertical = 10.dp)
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
                                        text = "Добавить",
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}