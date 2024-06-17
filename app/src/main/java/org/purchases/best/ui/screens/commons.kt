package org.purchases.best.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.purchases.best.R
import org.purchases.best.model.info.ListInfo
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.model.screens.home.HomeScreenContract
import org.purchases.best.model.screens.list.ListScreenContract
import org.purchases.best.settings.LIST_ID
import org.purchases.best.ui.navigation.NavigationItem
import org.purchases.best.ui.screens.home.HomeScreenViewModel
import org.purchases.best.ui.screens.list.ListScreenViewModel
import org.purchases.best.ui.theme.ScreenBackgroundColor
import org.purchases.best.utils.EMPTY

@Composable
fun MainProgressIndicator(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(listName: String, navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = listName,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = String.EMPTY
                )
            }
        }
    )
}

@SuppressLint("RestrictedApi")
@Composable
fun HomeScreenCard(
    list: ListInfo,
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = Color.White,
        shadowElevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val bundle = Bundle()
                bundle.putLong(LIST_ID, list.id)
                val destination =
                    navController.findDestination(NavigationItem.ListScreen.route)
                destination?.let { destinationNotNull ->
                    navController.navigate(destinationNotNull.id, bundle)
                }
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 5.dp)
        ) {
            Text(
                text = list.title,
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .weight(1f)
            )
            Box() {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = "More icon",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            expanded = true
                        }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Удалить") },
                        onClick = {
                            viewModel.handleEvent(
                                HomeScreenContract.Event.DeleteListButtonPressedEvent(
                                    list.id
                                )
                            )
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PurchaseRecordOnCreate(description: String) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = description,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun PurchaseRecord(purchaseInfo: PurchaseInfo, viewModel: ListScreenViewModel) {
    var isCheckedState by remember { mutableStateOf(purchaseInfo.checked) }
    Surface(
        color = if (isCheckedState) ScreenBackgroundColor else MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isCheckedState = isCheckedState.not()
                viewModel.handleEvent(
                    ListScreenContract.Event.TogglePurchaseEvent(
                        purchaseId = purchaseInfo.id
                    )
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isCheckedState,
                onCheckedChange = {
                    isCheckedState = isCheckedState.not()
                    viewModel.handleEvent(
                        ListScreenContract.Event.TogglePurchaseEvent(
                            purchaseId = purchaseInfo.id
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = purchaseInfo.description,
                textDecoration = if (isCheckedState) TextDecoration.LineThrough else null,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

