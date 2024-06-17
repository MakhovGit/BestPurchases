package org.purchases.best.ui.screens.create_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.purchases.best.R
import org.purchases.best.model.info.ListWithPurchasesInfo
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.model.screens.create_list.CreateListScreenContract
import org.purchases.best.settings.MAIN_LOG_TAG
import org.purchases.best.ui.navigation.NavigationItem
import org.purchases.best.ui.screens.PurchaseRecordOnCreate
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.LocalTitleColor
import org.purchases.best.ui.theme.PrimaryButtonColor
import org.purchases.best.ui.theme.ScreenBackgroundColor
import org.purchases.best.ui.theme.SecondaryButtonColor
import org.purchases.best.ui.theme.TextFieldBackgroundColor
import org.purchases.best.utils.EMPTY

@SuppressLint("RestrictedApi")
@Composable
fun CreateListScreen(
    viewModel: CreateListScreenViewModel,
    navController: NavController
) {
    var listTitle by remember { mutableStateOf(String.EMPTY) }
    var listItem by remember { mutableStateOf(String.EMPTY) }
    val purchases = SnapshotStateList<PurchaseInfo>()
    Surface(
        color = ScreenBackgroundColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            val textFieldShape = RoundedCornerShape(10.dp)
            val boxShape = RoundedCornerShape(10.dp)
            Text(
                text = "Создать новый список",
                color = LocalTitleColor,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = listTitle,
                onValueChange = { newValue ->
                    listTitle = newValue
                },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_list_alt_24),
                        contentDescription = "List icon"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = TextFieldBackgroundColor,
                    unfocusedContainerColor = TextFieldBackgroundColor,
                    focusedIndicatorColor = TextFieldBackgroundColor,
                    unfocusedIndicatorColor = TextFieldBackgroundColor
                ),
                shape = textFieldShape,
                placeholder = {
                    Text(text = "Название списка")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = textFieldShape
                    )
            )
            Spacer(modifier = Modifier.height(50.dp))
            Surface(
                shape = boxShape,
                color = TextFieldBackgroundColor,
                modifier = Modifier
                    .weight(1f)
                    .shadow(
                        elevation = 10.dp,
                        shape = boxShape
                    )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = listItem,
                        onValueChange = { newValue ->
                            listItem = newValue
                        },
                        singleLine = false,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_mail_outline_24),
                                contentDescription = "List icon"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.baseline_add_circle_24),
                                contentDescription = "List icon",
                                modifier = Modifier
                                    .size(35.dp)
                                    .clickable {
                                        if (listItem
                                                .trim()
                                                .isNotEmpty()
                                        ) {
                                            purchases.add(
                                                PurchaseInfo(
                                                    description = listItem,
                                                    checked = false
                                                )
                                            )
                                        }
                                        listItem = String.EMPTY
                                    }
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = TextFieldBackgroundColor,
                            unfocusedContainerColor = TextFieldBackgroundColor,
                            focusedIndicatorColor = TextFieldBackgroundColor,
                            unfocusedIndicatorColor = TextFieldBackgroundColor
                        ),
                        placeholder = {
                            Text(text = "Наименование")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Divider(modifier = Modifier.padding(horizontal = 20.dp))
                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        Log.d(MAIN_LOG_TAG, purchases.size.toString())
                        purchases.forEach {
                            item {
                                PurchaseRecordOnCreate(description = it.description)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier.padding(bottom = 50.dp)
            ) {
                Button(
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SecondaryButtonColor,
                        contentColor = ButtonTextColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 3.dp,
                        pressedElevation = 0.dp
                    ),
                    onClick = {
                        val destination =
                            navController.findDestination(NavigationItem.HomeScreen.route)
                        destination?.let { destinationNotNull ->
                            navController.navigate(destinationNotNull.id)
                        }
                    },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(
                        text = "Отмена",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 10.dp)
                    )
                }
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
                        val finalListTitle =
                            listTitle.takeIf { it.trim().isNotBlank() } ?: "Новый список"
                        viewModel.handleEvent(
                            CreateListScreenContract.Event.CreateNewListButtonPressedEvent(
                                ListWithPurchasesInfo(
                                    name = finalListTitle,
                                    purchasesNotChecked = purchases
                                )
                            )
                        )
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = "Создать",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}