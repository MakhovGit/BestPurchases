package org.purchases.add_item_screen.ui.add_item_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.purchases.add_item_screen.R
import org.purchases.core.model.info.PurchaseInfo
import org.purchases.core.utils.EMPTY
import org.purchases.core_ui.ui._elements.SimpleButton
import org.purchases.core_ui.ui.theme.LocalTitleColor
import org.purchases.core_ui.ui.theme.ScreenBackgroundColor
import org.purchases.core_ui.ui.theme.TextFieldBackgroundColor
import org.purchases.core_ui.utils.floatResource
import org.purchases.core_ui.R as core_ui_R

@SuppressLint("ResourceType")
@Composable
fun AddItemScreen(
    listId: Long,
    viewModel: AddItemScreenViewModel = koinViewModel(),
    navController: NavController,
) {
    var itemName by remember { mutableStateOf(String.EMPTY) }
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Surface(
                    color = ScreenBackgroundColor,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dimensionResource(id = core_ui_R.dimen.border_padding))
                    ) {
                        Text(
                            text = stringResource(id = R.string.title),
                            color = LocalTitleColor,
                            textAlign = TextAlign.Start,
                            fontSize = dimensionResource(id = core_ui_R.dimen.main_title_font_size).value.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(id = core_ui_R.dimen.main_title_bottom_padding))
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = core_ui_R.dimen.spacer_20)))
                        val textFieldShape =
                            RoundedCornerShape(dimensionResource(id = core_ui_R.dimen.rounded_corner_shape_text_field))
                        TextField(
                            value = itemName,
                            onValueChange = { newValue -> itemName = newValue },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(core_ui_R.drawable.baseline_list_alt_24),
                                    contentDescription = stringResource(id = R.string.icon_content_description)
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = TextFieldBackgroundColor,
                                unfocusedContainerColor = TextFieldBackgroundColor,
                                focusedIndicatorColor = TextFieldBackgroundColor,
                                unfocusedIndicatorColor = TextFieldBackgroundColor
                            ),
                            shape = textFieldShape,
                            placeholder = { Text(text = stringResource(id = R.string.placeholder)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = dimensionResource(id = core_ui_R.dimen.text_field_shadow_elevation),
                                    shape = textFieldShape
                                )
                        )
                        Spacer(
                            modifier = Modifier.weight(
                                floatResource(
                                    id = core_ui_R.dimen.max_weight
                                )
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = core_ui_R.dimen.button_row_space_between)),
                        ) {
                            SimpleButton(
                                text = stringResource(id = R.string.cancel_button),
                                isCancelButton = true,
                                onClick = { navController.navigateUp() },
                                modifier = Modifier.weight(
                                    floatResource(
                                        id = core_ui_R.dimen.half_weight
                                    )
                                )
                            )
                            SimpleButton(
                                text = stringResource(id = R.string.add_button),
                                onClick = {
                                    val finalItemTitle = itemName.trim()
                                    if (finalItemTitle.isNotBlank()) {
                                        viewModel.handleEvent(
                                            AddItemScreenContract.Event.AddItemButtonPressedEvent(
                                                listId = listId,
                                                purchase = PurchaseInfo(
                                                    listId = listId,
                                                    description = itemName,
                                                    checked = false
                                                )
                                            )
                                        )
                                    }
                                    navController.navigateUp()
                                },
                                modifier = Modifier.weight(
                                    floatResource(
                                        id = core_ui_R.dimen.half_weight
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}