package org.purchases.best.ui.screens.add_item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import org.purchases.best.model.info.PurchaseInfo
import org.purchases.best.model.screens.add_item.AddItemScreenContract
import org.purchases.best.ui.theme.ButtonTextColor
import org.purchases.best.ui.theme.Dimens
import org.purchases.best.ui.theme.LocalTitleColor
import org.purchases.best.ui.theme.PrimaryButtonColor
import org.purchases.best.ui.theme.ScreenBackgroundColor
import org.purchases.best.ui.theme.SecondaryButtonColor
import org.purchases.best.ui.theme.TextFieldBackgroundColor
import org.purchases.best.utils.EMPTY

@Composable
fun AddItemScreen(
    listId: Long,
    viewModel: AddItemScreenViewModel,
    navController: NavController
) {
    var itemTitle by remember { mutableStateOf(String.EMPTY) }
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
            Text(
                text = "Добавить в список",
                color = LocalTitleColor,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            val textFieldShape = RoundedCornerShape(10.dp)
            TextField(
                value = itemTitle,
                onValueChange = { newValue ->
                    itemTitle = newValue
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
                    Text(text = "Название")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = textFieldShape
                    )
            )
            Spacer(modifier = Modifier.weight(Dimens.Commons.MaxWeight))
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
                        navController.navigateUp()
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
                        val finalItemTitle = itemTitle.trim()
                        if (finalItemTitle.isNotBlank()) {
                            viewModel.handleEvent(
                                AddItemScreenContract.Event.AddItemButtonPressedEvent(
                                    listId = listId,
                                    purchase = PurchaseInfo(
                                        listId = listId,
                                        description = itemTitle,
                                        checked = false
                                    )
                                )
                            )
                        }
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = "Добавить",
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