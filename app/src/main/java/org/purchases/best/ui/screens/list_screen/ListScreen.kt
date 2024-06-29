package org.purchases.best.ui.screens.list_screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.purchases.best.R
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.settings.LIST_ID
import org.purchases.best.ui.navigation.NavigationItem
import org.purchases.best.ui.screens._elements.IconButton
import org.purchases.best.ui.screens._elements.PurchaseRecord
import org.purchases.best.ui.screens._elements.TopBar
import org.purchases.best.ui.theme.ScreenBackgroundColor
import org.purchases.best.utils.floatResource

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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = R.dimen.border_padding))
                    ) {
                        if (viewModel.screenState.data.purchasesChecked.isEmpty() &&
                            viewModel.screenState.data.purchasesNotChecked.isEmpty()
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(floatResource(id = R.dimen.max_weight))
                            ) {
                                Text(
                                    text = stringResource(id = R.string.list_screen_no_purchases),
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontSize = dimensionResource(id = R.dimen.no_elements_message_font_size).value.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = dimensionResource(id = R.dimen.no_elements_message_vertical_padding))
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(floatResource(id = R.dimen.max_weight))
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
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_20)))
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                text = stringResource(id = R.string.list_screen_add_button),
                                onClick = {
                                    val bundle = Bundle()
                                    bundle.putLong(LIST_ID, listId)
                                    val destination =
                                        navController.findDestination(NavigationItem.AddItemScreen.route)
                                    destination?.let { destinationNotNull ->
                                        navController.navigate(destinationNotNull.id, bundle)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}